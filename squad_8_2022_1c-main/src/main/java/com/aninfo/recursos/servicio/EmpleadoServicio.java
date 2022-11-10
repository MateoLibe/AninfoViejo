package com.aninfo.recursos.servicio;

import com.aninfo.recursos.modelo.Empleado;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class EmpleadoServicio {
    /**
     * Invokes the REST API from the course and returns a
     * collection of Empleados
     * @param - None
     * @return - ArrayList<Empleado>
     */
    public Collection<Empleado> getEmpleados() throws Throwable {
        // This is the URI For the Recursos endpoint assigned by the teacher
        String uri = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";

        //We create a REST Template to invoke the endpoint.
        RestTemplate restTemplate = new RestTemplate();

        // We get back the GET call and map it to our model class
        Empleado[] empleadoArray = restTemplate.getForObject(uri, Empleado[].class);

        return Arrays.asList(empleadoArray);
    }

    /**
     * Based on a legajo, invokes the REST API from the course
     * and filters it to return a single instance of employee
     * @param - legajo (long)
     * @return - Empleado
     */
    public Empleado getEmpleadoByLegajo(long legajo) throws Throwable{
        ArrayList<Empleado> empleados = new ArrayList<>(getEmpleados());

        //Filter based on Legajo
        Empleado empleadoBusco = empleados.stream()
            .filter(empleado -> Objects.equals(legajo, empleado.getLegajo()))
            .findAny()
            .orElse(null);
        return empleadoBusco;

    }


}
