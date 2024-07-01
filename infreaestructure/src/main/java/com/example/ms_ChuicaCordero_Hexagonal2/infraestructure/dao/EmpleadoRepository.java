package com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.dao;

import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.response.ResponseBase;
import com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {

    EmpleadoEntity findByNumDoc(String numDoc);

    Optional<List<EmpleadoEntity>> findByEstado(boolean estado);

    boolean existsByNumDoc(String numDoc);
}
