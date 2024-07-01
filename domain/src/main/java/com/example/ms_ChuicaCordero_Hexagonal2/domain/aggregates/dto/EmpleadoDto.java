package com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EmpleadoDto {


    private Long id;
    private String nombre;
    private String apellido;
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

    private String usuaCrea;

    private Timestamp dateCreate;

    private String usuaModif;

    private Timestamp dateModif;

    private String usuaDelet;

    private Timestamp dateDelet;
}
