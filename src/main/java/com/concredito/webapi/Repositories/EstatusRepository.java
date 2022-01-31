package com.concredito.webapi.Repositories;

import com.concredito.webapi.Models.EstatusModel;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//declarar la interfaz como repositorio y extender de la clase CrudRepository
@Repository
public interface EstatusRepository extends JpaRepository<EstatusModel,Long>{
    // public interface EstatusRepository extends CrudRepository<EstatusModel,Long>{
    
}
