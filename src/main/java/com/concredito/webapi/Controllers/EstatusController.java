package com.concredito.webapi.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.concredito.webapi.Models.EstatusModel;
import com.concredito.webapi.Services.EstatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/estatus")
public class EstatusController {
    // instanciar EstatusService
    @Autowired
    EstatusService estatusService;

    // obtener lista de todos los estatus
    @GetMapping
    public ArrayList<EstatusModel> getAll() {
        return estatusService.getAll();
    }

    // insertar nuevo estatus
    @PostMapping
    public EstatusModel create(EstatusModel entity) {
        return estatusService.create(entity);
    }

    // editar estatus existente
    @PutMapping
    public EstatusModel update(EstatusModel entity) {
        return estatusService.update(entity);
    }

    // eliminar estatus por id
    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        boolean result = estatusService.delete(id);
        if (result)
            return "Se ha eliminado el Estatus con Id: " + id;
        else
            return "No se ha podido eliminar el Estatus con Id: " + id;
    }

    //obtener estatus por id
    @GetMapping(path = "/{id}")
    public Optional<EstatusModel> getById(@PathVariable("id") Long id) {
        return estatusService.getById(id);
    }

}
