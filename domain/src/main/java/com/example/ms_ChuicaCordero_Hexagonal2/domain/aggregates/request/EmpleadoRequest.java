package com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoRequest {
    private Integer edad;
    private String cargo;
    private String tipoDoc;
    private String numDoc;
    private String departamento;
    private Double salario;
    private String telefono;
    private String correo;
    private boolean estado;
    private String direccion;
}
