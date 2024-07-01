package com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class ResponseBase {
    private int code;
    private String message;
    private Optional data;
}
