package com.aninfo.recursos.modelo;

import javax.persistence.*;

@Entity
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProyecto;
    private Long legajoEmpleadoACargo;

    public Proyecto(){
    }

    public Proyecto(Long idProyecto, Long empleadoACargo) throws Throwable {
        this.idProyecto = idProyecto;
        this.legajoEmpleadoACargo = legajoEmpleadoACargo;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long id) {
        this.idProyecto = id;
    }

    public Long getLegajoEmpleadoACargo() {
        return legajoEmpleadoACargo;
    }

    public void setLegajoEmpleadoACargo(Double hours) {
        this.legajoEmpleadoACargo = legajoEmpleadoACargo;
    }
}