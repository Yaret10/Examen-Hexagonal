package com.example.ms_ChuicaCordero_Hexagonal2.domain.ports.out;

import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.request.EmpleadoRequest;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.response.ResponseBase;


public interface EmpleadoServiceOut {

    ResponseBase crearEmpleadoOut(EmpleadoRequest empleadoRequest);

    ResponseBase obtenerEmpledadoOut(String dniEmpleado);

    ResponseBase eliminarEmpleado(String dniEmpleado);

    ResponseBase obtnerTodos();

    ResponseBase actualizarEmpleado(String numDoc, EmpleadoRequest empleadoRequest);
}
