package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.daos.VehiculoRepository;
import com.tecsup.prj_pc02.modelo.entidades.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    @Transactional
    public Vehiculo grabar(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        vehiculoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Vehiculo buscar(Integer id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }
}
