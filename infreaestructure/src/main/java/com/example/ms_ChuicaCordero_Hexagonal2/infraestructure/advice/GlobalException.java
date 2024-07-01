package com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.advice;

import com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.advice.personalizada.EmpleadoException;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.constans.Constants;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.response.ResponseBase;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
@Log4j2
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase> manejandoExepciones(Exception ex){
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR, "ERROR INTERNO DEL SERVIDOR: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseBase> NullPointer(NullPointerException ex){
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR, "ERROR HAY UN DATO NULO: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmpleadoException.class)
    public ResponseEntity<ResponseBase> manejandoPersonaException(EmpleadoException ex){
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR, "ERROR EN EL REGISTRO: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
