package com.tecsup.prj_pc02.controladores;

import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoMoto01;
import com.tecsup.prj_pc02.modelo.entidades.Ubicacion;
import com.tecsup.prj_pc02.servicios.EstacionamientoMoto01Service;
import com.tecsup.prj_pc02.servicios.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/moto")
@SessionAttributes("estacionamientoMoto01")
public class EstacionamientoMoto01Controller {

    @Autowired
    private EstacionamientoMoto01Service estacionamientoMoto01Service;

    @Autowired
    private UbicacionService ubicacionService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Estacionamientos Moto01");
        model.addAttribute("estacionamientos", estacionamientoMoto01Service.listar());
        return "MotolistarView";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String crear(Map<String, Object> model) {
        EstacionamientoMoto01 estacionamientoMoto01 = new EstacionamientoMoto01();
        model.put("estacionamientoMoto01", estacionamientoMoto01);
        model.put("ubicaciones", ubicacionService.listar());
        model.put("titulo", "Formulario de Estacionamiento Moto");
        return "MotoformView";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Map<String, Object> model) {
        EstacionamientoMoto01 estacionamientoMoto01 = estacionamientoMoto01Service.buscar(id);
        if (estacionamientoMoto01 == null) {
            return "redirect:/moto/listar";
        }
        model.put("estacionamientoMoto01", estacionamientoMoto01);
        model.put("ubicaciones", ubicacionService.listar());
        model.put("titulo", "Editar Estacionamiento Moto");
        return "MotoformView";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid EstacionamientoMoto01 estacionamientoMoto01, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Estacionamiento Moto");
            model.addAttribute("ubicaciones", ubicacionService.listar());
            return "MotoformView";
        }

        // Obtener la ubicación seleccionada por su ID
        if (estacionamientoMoto01.getUbicacion() != null && estacionamientoMoto01.getUbicacion().getId() != null) {
            Ubicacion ubicacion = ubicacionService.buscar(estacionamientoMoto01.getUbicacion().getId());
            estacionamientoMoto01.setUbicacion(ubicacion);
        }

        estacionamientoMoto01Service.grabar(estacionamientoMoto01);
        status.setComplete();
        return "redirect:/moto/listar";
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            estacionamientoMoto01Service.eliminar(id);
        }
        return "redirect:/moto/listar";
    }

    @RequestMapping(value = "/ver", method = RequestMethod.GET)
    public String ver(@RequestParam(value = "format", required = false) String format, Model model) {
        model.addAttribute("estacionamientos", estacionamientoMoto01Service.listar());
        model.addAttribute("titulo", "Lista de estacionamientos Moto01");

        if ("pdf".equalsIgnoreCase(format)) {
            return "estacionamientoMoto/verPdf"; //
        } else if ("xlsx".equalsIgnoreCase(format)) {
            return "estacionamientoMoto/verXls"; //
        }

        return "estacionamientoMoto/ver";
    }
}
