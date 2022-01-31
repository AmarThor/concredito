package com.concredito.webapi.Repositories;

import java.util.ArrayList;

import com.concredito.webapi.Models.DocumentoModel;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, Long> {
    // public interface DocumentoRepository extends CrudRepository<DocumentoModel, Long> {
    public abstract ArrayList<DocumentoModel> findDocumentoModelByProspecto_Id(Long prospecto_Id);
}
