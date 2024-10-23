package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.daos.UbicacionRepository;
import com.tecsup.prj_pc02.modelo.entidades.Ubicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UbicacionServiceImpl implements UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Ubicacion> listar() {
        return (List<Ubicacion>) ubicacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Ubicacion buscar(Integer id) {
        return ubicacionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void grabar(Ubicacion ubicacion) {
        ubicacionRepository.save(ubicacion);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        ubicacionRepository.deleteById(id);
    }
}
