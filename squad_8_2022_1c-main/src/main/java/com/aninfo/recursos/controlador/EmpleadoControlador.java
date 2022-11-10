package com.aninfo.recursos.controlador;
import com.aninfo.recursos.modelo.Empleado;
import com.aninfo.recursos.servicio.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@EnableSwagger2
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    /**
     * Endpoint related to Employee (Empleado), works as a GET REST API
     * Method and retrieves information from course guide endpoint.
     * @param - None
     * @return - Collection<Empleado>
     *
     */
    @GetMapping("/empleado")
    public Collection<Empleado> getEmpleado() throws Throwable {
        return empleadoServicio.getEmpleados();
    }

    /**
     * Endpoint related to Employee (Empleado), works as a GET REST API
     * Method and retrieves information from course guide endpoint returning
     * one employee based on it's id (legajo).
     * @param - legajo (long)
     * @return - Empleado
     *
     */
    @GetMapping("/empleado/{legajo}")
    public Empleado getOneEmpleado(long legajo) throws Throwable {
        return empleadoServicio.getEmpleadoByLegajo(legajo);
    }

}
