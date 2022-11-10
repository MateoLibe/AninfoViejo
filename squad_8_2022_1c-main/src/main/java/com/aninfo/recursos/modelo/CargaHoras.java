package com.aninfo.recursos.modelo;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "horas", schema="public")
public class CargaHoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCarga;
    @Column(name = "horas")
    private Double horas;
    @Column(name = "id_tarea")
    private Long tarea;
    @Column(name = "id_proyecto")
    private Long proyecto;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "legajo")
    private Long legajoPersona;
    @Column(name = "nombre_tarea")
    private String nombreTarea; //debe ir aca?

    public CargaHoras(){
    }

    public CargaHoras(Double hours, Long task, Long project, Date date, Long legajo, String nombre) throws Throwable {
        this.horas = hours;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.fecha = sdf.parse(sdf.format(date));
        this.tarea = task;
        this.proyecto = project;
        this.legajoPersona = legajo;
        this.nombreTarea = nombre;
    }

    public Long getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(Long id) {
        this.idCarga = id;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double hours) {
        this.horas = hours;
    }

    public Date getFecha(){ return fecha; }

    public void setFecha(Date date) { this.fecha = date; }

    public Long getTarea(){ return tarea; }

    public void setTarea(Long task){ this.tarea = task; }

    public Long getProyecto(){ return proyecto; }

    public void setProyecto(Long project){ this.proyecto = project; }

    public Long getLegajoPersona(){ return legajoPersona; }

    public void setLegajoPersona(Long legajo){ this.legajoPersona = legajo; }

    public String getNombreTarea() { return nombreTarea; }

    public void setNombreTarea(String nombre){ this.nombreTarea = nombre; }

}
