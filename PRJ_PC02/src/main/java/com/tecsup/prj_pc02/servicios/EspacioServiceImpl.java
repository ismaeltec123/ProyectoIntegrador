package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.daos.EspacioRepository;
import com.tecsup.prj_pc02.modelo.dto.AsignacionEspacioDTO;
import com.tecsup.prj_pc02.modelo.entidades.Espacio;
import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tecsup.prj_pc02.modelo.entidades.EstadoEspacio;
import java.util.List;
import java.util.Optional;

@Service
public class EspacioServiceImpl implements EspacioService {

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    private EstacionamientoService estacionamientoService;

    @Autowired
    private EspacioRepository dao;

    @Override
    @Transactional(readOnly = false)
    public Espacio grabar(Espacio espacio) {
        return dao.save(espacio);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Espacio buscar(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Espacio> listar() {
        return (List<Espacio>) dao.findAll();
    }
    @Override
    @Transactional
    public Espacio asignarEspacio(AsignacionEspacioDTO dto) {
        Estacionamiento estacionamiento = estacionamientoService.buscar(dto.getEstacionamientoId());

        if (estacionamiento == null) {
            throw new IllegalArgumentException("Estacionamiento no encontrado.");
        }

        // Buscar un espacio disponible
        List<Espacio> espaciosDisponibles = espacioRepository.findByEstacionamientoAndEstado(estacionamiento, EstadoEspacio.disponible);

        if (espaciosDisponibles.isEmpty()) {
            throw new IllegalStateException("No hay espacios disponibles en este estacionamiento.");
        }

        // Asignar el primer espacio disponible
        Espacio espacioAsignado = espaciosDisponibles.get(0);
        espacioAsignado.setEstado(EstadoEspacio.ocupado);

        return espacioRepository.save(espacioAsignado);
    }

    @Override
    @Transactional
    public Espacio liberarEspacio(Integer espacioId) {
        Espacio espacio = espacioRepository.findById(espacioId)
                .orElseThrow(() -> new IllegalArgumentException("Espacio no encontrado."));

        espacio.setEstado(EstadoEspacio.disponible);
        return espacioRepository.save(espacio);
    }

    @Override
    @Transactional
    public Optional<Espacio> buscarEspacioPorId(Integer id) {
        return espacioRepository.findById(id);
    }
}
