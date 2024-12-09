package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.daos.EstacionamientoRepository;
import com.tecsup.prj_pc02.modelo.dto.EstacionamientoDTO;
import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstacionamientoServiceImpl implements EstacionamientoService {

    @Autowired
    private EstacionamientoRepository dao;

    @Override
    @Transactional(readOnly = false)
    public Estacionamiento grabar(Estacionamiento estacionamiento) {
        // Guardar el objeto y devolver el mismo objeto persistido
        return dao.save(estacionamiento);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Estacionamiento buscar(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Estacionamiento> listar() {
        return (List<Estacionamiento>) dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstacionamientoDTO> listarConCapacidadDisponible() {
        return dao.findEstacionamientosConCapacidadDisponible()
                .stream()
                .map(obj -> new EstacionamientoDTO(
                        ((Number) obj[0]).intValue(), // pk_id_estacionamiento
                        (String) obj[1],             // nombre
                        (String) obj[2],             // tipo
                        ((Number) obj[3]).intValue() // capacidadDisponible
                ))
                .collect(Collectors.toList());
    }
}
