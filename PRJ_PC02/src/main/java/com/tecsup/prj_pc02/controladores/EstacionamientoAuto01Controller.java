package com.tecsup.prj_pc02.controladores;

import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoAuto01;
import com.tecsup.prj_pc02.modelo.entidades.Ubicacion;
import com.tecsup.prj_pc02.servicios.EstacionamientoAuto01Service;
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
@CrossOrigin(origins = "*")
@RequestMapping("/auto")
@SessionAttributes("estacionamientoAuto01")
public class EstacionamientoAuto01Controller {

    @Autowired
    private EstacionamientoAuto01Service estacionamientoAuto01Service;

    @Autowired
    private UbicacionService ubicacionService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Estacionamientos Auto01");
        model.addAttribute("estacionamientos", estacionamientoAuto01Service.listar());
        return "listarView";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String crear(Map<String, Object> model) {
        EstacionamientoAuto01 estacionamientoAuto01 = new EstacionamientoAuto01();
        model.put("estacionamientoAuto01", estacionamientoAuto01);
        model.put("ubicaciones", ubicacionService.listar());
        model.put("titulo", "Formulario de Estacionamiento");
        return "formView";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Map<String, Object> model) {
        EstacionamientoAuto01 estacionamientoAuto01 = estacionamientoAuto01Service.buscar(id);
        if (estacionamientoAuto01 == null) {
            return "redirect:/auto/listar";
        }
        model.put("estacionamientoAuto01", estacionamientoAuto01);
        model.put("ubicaciones", ubicacionService.listar());
        model.put("titulo", "Editar Estacionamiento");
        return "formView";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid EstacionamientoAuto01 estacionamientoAuto01, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Estacionamiento");
            model.addAttribute("ubicaciones", ubicacionService.listar());
            return "formView";
        }
        if (estacionamientoAuto01.getUbicacion() != null && estacionamientoAuto01.getUbicacion().getId() != null) {
            Ubicacion ubicacion = ubicacionService.buscar(estacionamientoAuto01.getUbicacion().getId());
            estacionamientoAuto01.setUbicacion(ubicacion);
        }

        estacionamientoAuto01Service.grabar(estacionamientoAuto01);
        status.setComplete();
        return "redirect:/auto/listar";
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            estacionamientoAuto01Service.eliminar(id);
        }
        return "redirect:/auto/listar";
    }

    @RequestMapping(value = "/ver", method = RequestMethod.GET)
    public String ver(@RequestParam(value = "format", required = false) String format, Model model) {
        model.addAttribute("estacionamientos", estacionamientoAuto01Service.listar());
        model.addAttribute("titulo", "Lista de estacionamientos");

        if ("pdf".equalsIgnoreCase(format)) {
            return "estacionamiento/verPdf";
        } else if ("xlsx".equalsIgnoreCase(format)) {
            return "estacionamiento/verXls";
        }

        return "estacionamiento/ver";
    }
}
