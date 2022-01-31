package com.concredito.webapi.Services;

import java.util.ArrayList;
import java.util.Optional;

import com.concredito.webapi.Models.ProspectoModel;
import com.concredito.webapi.Repositories.ProspectoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProspectoService {

    @Autowired
    ProspectoRepository prospectoRepository;

    //obtener listado de prospectos
    public ArrayList<ProspectoModel> getAll(){
        return (ArrayList<ProspectoModel>) prospectoRepository.findAll();
    }

    public Page<ProspectoModel> getPaginated(Optional<Integer> page,Optional<Integer> pageSize){
        return prospectoRepository.findAll(PageRequest.of( page.orElse(0),pageSize.orElse(10)));
    }

    //crear nuevo prospecto
    public ProspectoModel create(ProspectoModel entity){
        return prospectoRepository.save(entity);
    }

    //editar prospecto existente
    public ProspectoModel update(ProspectoModel entity){
        return prospectoRepository.save(entity);
    }

    //eliminar prospecto por id
    public boolean delete(Long id){
        try {
            prospectoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //obtener prospecto por Id
    public Optional<ProspectoModel> getById(Long id){
        return  prospectoRepository.findById(id);
    }
    
}
