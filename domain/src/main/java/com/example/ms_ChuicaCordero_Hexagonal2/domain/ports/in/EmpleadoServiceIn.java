package com.example.ms_ChuicaCordero_Hexagonal2.domain.ports.in;

import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.request.EmpleadoRequest;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.response.ResponseBase;


public interface EmpleadoServiceIn {

    ResponseBase crearEmpleadoIn(EmpleadoRequest empleadoRequest);

    ResponseBase obtenerEmpledadoIn(String dniEmpleado);

    ResponseBase eliminarEmpleado(String dniEmpleado);

    ResponseBase obtnerTodos();

    ResponseBase actualizarEmpleado(String numDoc, EmpleadoRequest empleadoRequest);

}
