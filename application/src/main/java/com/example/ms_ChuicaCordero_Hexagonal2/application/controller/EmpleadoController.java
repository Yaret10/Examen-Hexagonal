package com.example.ms_ChuicaCordero_Hexagonal2.application.controller;

import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.constans.Constants;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.request.EmpleadoRequest;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.response.ResponseBase;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.ports.in.EmpleadoServiceIn;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/empleado")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoServiceIn empleadoServiceIn;

    @PostMapping
    public ResponseEntity<ResponseBase> crearEmpleado(@RequestBody EmpleadoRequest empleadoRequest) {
        ResponseBase responseBase = empleadoServiceIn.crearEmpleadoIn(empleadoRequest);
        if (responseBase.getCode() == Constants.CODIGO_EXITO){
            return ResponseEntity.status(HttpStatus.CREATED).body(responseBase);
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }

    }
    @GetMapping("/buscar/{numDoc}")
    public ResponseEntity<ResponseBase> obtenerEmpleado(@PathVariable String numDoc){
        ResponseBase responseBase = empleadoServiceIn.obtenerEmpledadoIn(numDoc);
        if (responseBase.getCode() == Constants.CODIGO_EXITO){
            return ResponseEntity.ok(responseBase);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

    @DeleteMapping("/eliminar/{numDoc}")
    public ResponseEntity<ResponseBase> eliminarEmpleado(@PathVariable String numDoc){
        ResponseBase responseBase = empleadoServiceIn.eliminarEmpleado(numDoc);
        if (responseBase.getCode() == Constants.CODIGO_EXITO){
            return ResponseEntity.ok(responseBase);
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

    @GetMapping("/obtenerTodos/")
    public ResponseEntity<ResponseBase> obtenerTodos(){
        ResponseBase responseBase = empleadoServiceIn.obtnerTodos();
        if (responseBase.getCode()==Constants.CODIGO_EXITO){
            return ResponseEntity.ok(responseBase);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

    @PutMapping("/actualizar/{numDoc}")
    public ResponseEntity<ResponseBase> actualizarEmple(@PathVariable String numDoc, @RequestBody EmpleadoRequest empleadoRequest){
        ResponseBase responseBase = empleadoServiceIn.actualizarEmpleado(numDoc, empleadoRequest);
        if (responseBase.getCode() == Constants.CODIGO_EXITO){
            return ResponseEntity.ok(responseBase);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

}
