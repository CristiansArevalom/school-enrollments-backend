package com.carevalom.school.school.exception;


public class ModelNotFoundException  extends RuntimeException{
    //esta clase maneja el mensaje de error 
    public ModelNotFoundException(String message){
        super(message);
    }

}
