package com.concredito.webapi.Services;

import java.util.ArrayList;
import java.util.Optional;

import com.concredito.webapi.Models.EstatusModel;
import com.concredito.webapi.Repositories.EstatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstatusService {
    @Autowired
    // instanciar repositorio
    EstatusRepository estatusRepository;

    // obtener listado de estatus
    public ArrayList<EstatusModel> getAll() {
        return (ArrayList<EstatusModel>) estatusRepository.findAll();
    }

    // guardar un nuevo objeto de tipo EstatusModel
    public EstatusModel create(EstatusModel entity) {
        return estatusRepository.save(entity);
    }

    // actualizar un objeto existente
    public EstatusModel update(EstatusModel entity) {
        return estatusRepository.save(entity);
    }

    // eliminar un objeto a partir del id
    public boolean delete(Long id) {
        try {
            estatusRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // obtener estatus a partir del id
    public Optional<EstatusModel> getById(Long id) {
        return estatusRepository.findById(id);
    }
}
