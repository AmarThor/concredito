package com.concredito.webapi.Repositories;

import com.concredito.webapi.Models.ProspectoModel;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectoRepository extends JpaRepository<ProspectoModel,Long> {
    // public interface ProspectoRepository extends CrudRepository<ProspectoModel,Long> {
    
}
