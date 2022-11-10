package stepDefinitions;

import com.aninfo.recursos.Application;
import com.aninfo.recursos.excepciones.CantidadDeDiasInvalidaException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import  com.aninfo.recursos.excepciones.CantidadDeHorasInvalidaException;
import com.aninfo.recursos.modelo.CargaHoras;
import com.aninfo.recursos.servicio.CargaHorasServicio;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class OperacionesRecursos{

    @Autowired
    private CargaHorasServicio serv;
    private CargaHoras horas_empleado = new CargaHoras();
    private CantidadDeHorasInvalidaException err = null;
    private CantidadDeDiasInvalidaException errD = null;
    private long id = 1;


    @Given("^un empleado$")
    public void un_empleado(){

    }

    @When("^carga (\\d+) horas un martes$")
    public void carga_horas_un_martes(int horas) throws Throwable{

        try{

            horas_empleado.setFecha(new Date(122, 05, 28));
            horas_empleado.setHoras((double)horas);
            serv.createCargaHoras(horas_empleado);

        } catch (CantidadDeHorasInvalidaException err){

            this.err = err;

        }

    }

    @Then("^se cargan (\\d+) horas$")
    public void se_cargan_horas(int cant_supuestas){

        assertEquals(cant_supuestas, serv.findById(id).get().getHoras());

    }

    @Given("^una cantidad de horas cargadas a una tarea$")
    public void una_cantidad_de_horas_cargadas_a_una_tarea() throws Throwable{

        try{

            horas_empleado.setIdCarga(id);
            horas_empleado.setHoras((double) 1);
            horas_empleado.setFecha(new Date(122, 05, 28));

            serv.createCargaHoras(horas_empleado);

        }catch (CantidadDeHorasInvalidaException err){

            this.err = err;

        }

    }

    @When("^se la modifica a (\\d+)$")
    public void se_la_modifica_a(int cant_nueva) throws Throwable{

        try {

            double nuevaCarga = cant_nueva;

            serv.modifyHoras(id, nuevaCarga);

        }catch (CantidadDeHorasInvalidaException err){

            this.err = err;

        }

    }

    @Then("^las horas modificadas se mantienen en (\\d+)$")
    public void las_horas_modificadas_se_mantienen_en(int horas_supuestas){

        assertEquals(horas_supuestas, serv.findById(id).get().getHoras());

    }

    @When("^carga una cantidad negativa de (\\d+) horas$")
    public void carga_una_cantidad_negativa_de_horas(int horas_cargadas) throws Throwable {

        try{

            horas_empleado.setIdCarga(id);
            horas_empleado.setHoras((double)(-horas_cargadas));
            serv.createCargaHoras(horas_empleado);

        }catch (CantidadDeHorasInvalidaException err){

            this.err = err;

        }

    }

    @Then("^sale un error de cantidad invalida de horas$")
    public void sale_un_error_de_cantidad_invalida_de_horas(){

        assertNotNull(this.err);

    }

    @When("^se la modifica con el negativo de (\\d+)$")
    public void se_la_modifica_con_el_negativo_de(int cant_nueva) throws Throwable {

        try {

            horas_empleado.setIdCarga(id);
            horas_empleado.setHoras((double)(-cant_nueva));
            serv.createCargaHoras(horas_empleado);

        }catch (CantidadDeHorasInvalidaException err){

            this.err = err;

        }

    }

    @When("carga {int} horas para dentro de un mes falla")
    public void carga_horas_para_dentro_de_un_mes_falla(Integer int1) throws Throwable{
        try{

            horas_empleado.setIdCarga(id);
            horas_empleado.setHoras((double) 1);
            horas_empleado.setFecha(new Date(122, 06, 28));

            serv.createCargaHoras(horas_empleado);

        }catch (CantidadDeDiasInvalidaException errd){

            this.errD = errd;

        }
    }

    @Then("sale un error de dias invalidos")
    public void sale_un_error_de_dias_invalidos() {
        assertNotNull(this.errD);
    }

    @When("carga {int} horas ya pasados {int} dias")
    public void carga_horas_ya_pasados_dias(Integer int1, Integer int2) throws Throwable{
        try{

            horas_empleado.setIdCarga(id);
            horas_empleado.setHoras((double) 1);
            horas_empleado.setFecha(new Date(122, 04, 28));

            serv.createCargaHoras(horas_empleado);

        }catch (CantidadDeDiasInvalidaException errd){

            this.errD = errd;

        }
    }

}
