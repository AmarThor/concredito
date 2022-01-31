package com.concredito.webapi.Models;

import javax.persistence.*;


@Entity
@Table(name = "estatus")
public class EstatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String descripcion;


    // public EstatusModel( String descripcion) {
	// 	super();
	// 	this.descripcion = descripcion;
	// }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
