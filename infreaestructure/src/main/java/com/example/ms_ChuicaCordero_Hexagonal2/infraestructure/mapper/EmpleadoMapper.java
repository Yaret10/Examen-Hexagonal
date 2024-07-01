package com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.mapper;

import com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.entity.EmpleadoEntity;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.dto.EmpleadoDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public EmpleadoDto mapToDto(EmpleadoEntity empleadoEntity){
        return modelMapper.map(empleadoEntity, EmpleadoDto.class);
    }

    public EmpleadoEntity mapToEntity(EmpleadoDto empleadoDto){
        return modelMapper.map(empleadoDto, EmpleadoEntity.class);
    }
}
