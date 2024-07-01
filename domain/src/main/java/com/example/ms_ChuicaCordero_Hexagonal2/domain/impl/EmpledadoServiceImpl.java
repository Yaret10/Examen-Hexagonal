package com.example.ms_ChuicaCordero_Hexagonal2.domain.impl;

import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.request.EmpleadoRequest;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.response.ResponseBase;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.ports.in.EmpleadoServiceIn;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.ports.out.EmpleadoServiceOut;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpledadoServiceImpl implements EmpleadoServiceIn {

    private final EmpleadoServiceOut empleadoServiceOut;

    @Override
    public ResponseBase crearEmpleadoIn(EmpleadoRequest empleadoRequest) {
        return empleadoServiceOut.crearEmpleadoOut(empleadoRequest);
    }

    @Override
    public ResponseBase obtenerEmpledadoIn(String dniEmpleado) {
        return empleadoServiceOut.obtenerEmpledadoOut(dniEmpleado);
    }

    @Override
    public ResponseBase eliminarEmpleado(String dniEmpleado) {
        return empleadoServiceOut.eliminarEmpleado(dniEmpleado);
    }

    @Override
    public ResponseBase obtnerTodos() {
        return empleadoServiceOut.obtnerTodos();
    }

    @Override
    public ResponseBase actualizarEmpleado(String numDoc, EmpleadoRequest empleadoRequest) {
        return empleadoServiceOut.actualizarEmpleado(numDoc,empleadoRequest);
    }
}
