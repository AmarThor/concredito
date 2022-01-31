package com.concredito.webapi.Controllers;

import java.util.Optional;

import com.concredito.webapi.Models.ProspectoModel;
import com.concredito.webapi.Services.ProspectoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/prospecto")
public class ProspectoController {

    @Autowired
    ProspectoService prospectoService;

    // // obtener lista de todos los prospectos
    // @GetMapping
    // public ArrayList<ProspectoModel> getAll() {
    // return prospectoService.getAll();
    // }

    @GetMapping
    public Page<ProspectoModel> getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        return prospectoService.getPaginated(page,pageSize);
    }

    // crear nuevo prospecto
    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ProspectoModel create(@RequestBody ProspectoModel entity) {
        return prospectoService.create(entity);
    }

    // editar prospecto existente
    @PutMapping
    public ProspectoModel update(@RequestBody ProspectoModel entity) {
        return prospectoService.update(entity);
    }

    // eliminar prospecto por id
    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        boolean result = prospectoService.delete(id);
        if (result)
            return "Se ha eliminado el prospecto con id: " + id;
        else
            return "No se ha podido eliminar el prospecto con id: " + id;
    }

    // obtener prospecto por id
    @GetMapping(path = "/{id}")
    public Optional<ProspectoModel> getById(Long id) {
        return prospectoService.getById(id);
    }
}
