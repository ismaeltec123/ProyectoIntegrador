package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.daos.EstacionamientoMoto01Repository;
import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoMoto01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstacionamientoMoto01ServiceImpl implements EstacionamientoMoto01Service {

    @Autowired
    private EstacionamientoMoto01Repository dao;

    @Override
    @Transactional(readOnly = false)
    public void grabar(EstacionamientoMoto01 estacionamientoMoto01) {
        dao.save(estacionamientoMoto01);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public EstacionamientoMoto01 buscar(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstacionamientoMoto01> listar() {
        return (List<EstacionamientoMoto01>) dao.findAll();
    }
}
