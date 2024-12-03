package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.daos.EspacioRepository;
import com.tecsup.prj_pc02.modelo.entidades.Espacio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspacioServiceImpl implements EspacioService {

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
}
