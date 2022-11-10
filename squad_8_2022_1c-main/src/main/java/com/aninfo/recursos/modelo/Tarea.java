package com.aninfo.recursos.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;
    private Long idProyecto;
    private String nombre;

    public Tarea(){

    }

    public Tarea(Long id, Long proyecto, String nom){

        this.idTarea = id;
        this.idProyecto = proyecto;
        this.nombre = nom;

    }

    public void setId(Long id){

        this.idTarea = id;

    }

    public void setIdProyecto(Long id){

        this.idProyecto = id;

    }

    public void setNombre(String nom){

        this.nombre = nom;

    }

    public Long getId(){

        return this.idTarea;

    }

    public Long getIdProyecto(){

        return this.idProyecto;

    }

    public  String getNombre(){

        return this.nombre;

    }

}
