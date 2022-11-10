package  com.aninfo.recursos.excepciones;

public class CantidadDeHorasInvalidaException extends RuntimeException{

    public CantidadDeHorasInvalidaException(){

        System.out.println("La cantidad especificada es incorrecta. La cantidad aceptable debe ser un numero de 1 a 8");

    }
}
