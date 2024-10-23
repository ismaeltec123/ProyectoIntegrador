package com.tecsup.prj_pc02.servicios;



import com.tecsup.prj_pc02.modelo.daos.EstacionamientoAuto01Repository;
import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoAuto01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EstacionamientoAuto01ServiceImpl implements EstacionamientoAuto01Service {

    @Autowired
    private EstacionamientoAuto01Repository dao;

    @Override
    @Transactional(readOnly = false)
    public EstacionamientoAuto01 grabar(EstacionamientoAuto01 estacionamientoAuto01) {
        // Guardar el objeto y devolver el mismo objeto persistido
        return dao.save(estacionamientoAuto01);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public EstacionamientoAuto01 buscar(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstacionamientoAuto01> listar() {
        return (List<EstacionamientoAuto01>) dao.findAll();
    }
}


