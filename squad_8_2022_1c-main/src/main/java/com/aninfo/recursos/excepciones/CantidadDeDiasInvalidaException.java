package com.aninfo.recursos.excepciones;

public class CantidadDeDiasInvalidaException extends RuntimeException{

    public CantidadDeDiasInvalidaException(){

        System.out.println("La fecha especificada es incorrecta. Debe estar contenida hasta unos 30 dias antes de la fecha de hoy");

    }
}
