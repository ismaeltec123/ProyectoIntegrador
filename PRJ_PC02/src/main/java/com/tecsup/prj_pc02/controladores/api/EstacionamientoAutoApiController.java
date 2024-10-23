package com.tecsup.prj_pc02.controladores.api;

import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoAuto01;
import com.tecsup.prj_pc02.modelo.entidades.Ubicacion;
import com.tecsup.prj_pc02.servicios.EstacionamientoAuto01Service;
import com.tecsup.prj_pc02.servicios.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/auto")
public class EstacionamientoAutoApiController {

    @Autowired
    private EstacionamientoAuto01Service estacionamientoAuto01Service;

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping("/listar")
    public List<EstacionamientoAuto01> listar() {
        return estacionamientoAuto01Service.listar();
    }

    @PostMapping("/crear")
    public EstacionamientoAuto01 crear(@RequestBody Map<String, Object> requestData) {
        // Extraemos los datos del request (incluyendo ubicacionId)
        String idslot = (String) requestData.get("idslot");
        String placa = (String) requestData.get("placa");
        Integer ubicacionId = (Integer) requestData.get("ubicacionId");

        // Creamos la nueva entidad y asignamos los valores
        EstacionamientoAuto01 estacionamiento = new EstacionamientoAuto01();
        estacionamiento.setIdslot(idslot);
        estacionamiento.setPlaca(placa);

        // Buscamos la ubicación por el ID y la asignamos a la entidad
        Ubicacion ubicacion = ubicacionService.buscar(ubicacionId);
        estacionamiento.setUbicacion(ubicacion);

        // Guardamos la entidad en la base de datos
        estacionamientoAuto01Service.grabar(estacionamiento);

        return estacionamiento;
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        estacionamientoAuto01Service.eliminar(id);
    }

}

