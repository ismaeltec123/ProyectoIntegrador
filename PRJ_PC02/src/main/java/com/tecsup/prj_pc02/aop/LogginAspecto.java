

package com.tecsup.prj_pc02.aop;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.Calendar;
import com.tecsup.prj_pc02.modelo.daos.AuditoriaRepository;
import com.tecsup.prj_pc02.modelo.entidades.Auditoria;
import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoAuto01;
import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoMoto01;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogginAspecto {

    private Long tx;

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Around("execution(* com.tecsup.prj_pc02.servicios.*ServiceImpl.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result =  null;
        Long currTime = System.currentTimeMillis();
        tx = System.currentTimeMillis();
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = "tx[" + tx + "] - " + joinPoint.getSignature().getName();
        if (joinPoint.getArgs().length > 0)
            logger.info(metodo + "() INPUT:" + Arrays.toString(joinPoint.getArgs()));
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage());
            throw e;
        }
        logger.info(metodo + "(): tiempo transcurrido " + (System.currentTimeMillis() - currTime) + " ms.");
        return result;
    }

    @After("execution(* com.tecsup.prj_pc02.controladores.*Controller.guardar*(..)) ||" +
            "execution(* com.tecsup.prj_pc02.controladores.*Controller.editar*(..)) ||" +
            "execution(* com.tecsup.prj_pc02.controladores.*Controller.eliminar*(..))")
    public void auditoria(JoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Integer id = null;
        String tabla = "desconocido";


        if (args.length > 0) {
            Object entity = args[0];


            if (entity instanceof EstacionamientoAuto01) {
                EstacionamientoAuto01 auto = (EstacionamientoAuto01) entity;
                id = auto.getIdEstacionamientoAuto01();
                tabla = "EstacionamientoAuto01";
                logger.info("Auditoría para EstacionamientoAuto01: " + auto.toString());


            } else if (entity instanceof EstacionamientoMoto01) {
                EstacionamientoMoto01 moto = (EstacionamientoMoto01) entity;
                id = moto.getIdEstacionamientoMoto01();
                tabla = "EstacionamientoMoto01";
                logger.info("Auditoría para EstacionamientoMoto01: " + moto.toString());


            } else if (entity instanceof Integer) {
                id = (Integer) entity;


                String className = joinPoint.getTarget().getClass().getSimpleName();
                if (className.contains("Auto")) {
                    tabla = "EstacionamientoAuto01";
                } else if (className.contains("Moto")) {
                    tabla = "EstacionamientoMoto01";
                }

                logger.info("Auditoría para eliminación en la tabla: " + tabla + " con ID: " + id);
            }
        }


        if (id != null) {
            auditoriaRepository.save(new Auditoria(tabla, id, Calendar.getInstance().getTime(),
                    "usuario", metodo));
            logger.info("Auditoría registrada: Tabla - " + tabla + ", ID - " + id + ", Método - " + metodo);
        } else {
            logger.error("No se pudo registrar la auditoría: ID no encontrado.");
        }
    }
}
