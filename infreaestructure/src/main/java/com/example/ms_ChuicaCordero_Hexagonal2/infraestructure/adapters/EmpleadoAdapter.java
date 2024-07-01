package com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.adapters;

import com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.advice.personalizada.EmpleadoException;
import com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.dao.EmpleadoRepository;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.constans.Constants;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.dto.EmpleadoDto;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.request.EmpleadoRequest;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.response.ResponseBase;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.aggregates.response.ResponseReniec;
import com.example.ms_ChuicaCordero_Hexagonal2.domain.ports.out.EmpleadoServiceOut;
import com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.entity.EmpleadoEntity;
import com.example.ms_ChuicaCordero_Hexagonal2.infraestructure.mapper.EmpleadoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoAdapter implements EmpleadoServiceOut {

    private final EmpleadoRepository empleadoRepository;
    private final RestTemplate restTemplate;

    private final EmpleadoMapper empleadoMapper;

    @Value("${token}")
    private String tokenApi;

    @Override
    public ResponseBase crearEmpleadoOut(EmpleadoRequest empleadoRequest) {
        EmpleadoEntity empleadoEntity = getEntityRestTemplate(empleadoRequest);
        if(empleadoEntity != null){
            boolean exist =empleadoRepository.existsByNumDoc(empleadoRequest.getNumDoc());
            if (exist){
                throw new EmpleadoException("Empleado: "+empleadoRequest.getNumDoc()+ " ya existe en la Base de Datos");
            }else {
                EmpleadoDto empleadoDto = empleadoMapper.mapToDto(empleadoRepository.save(empleadoEntity));
                return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITO, Optional.of(empleadoDto));
            }
        }else {
            return new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_ERROR,Optional.empty());
        }
    }

    @Override
    public ResponseBase obtenerEmpledadoOut(String dniEmpleado) {
            EmpleadoEntity empleadoEntity = empleadoRepository.findByNumDoc(dniEmpleado);
            if (empleadoEntity != null){
                EmpleadoDto empleadoDto1 = empleadoMapper.mapToDto(empleadoEntity);
                return  new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITO_BUSCAR, Optional.of(empleadoDto1));
            }else {
                throw new NullPointerException(dniEmpleado);
                //return new ResponseBase(Constants.CODIGO_ERROR, Constants.NOT_FOUND,Optional.empty());
            }
    }

    @Override
    public ResponseBase eliminarEmpleado(String dniEmpleado) {
        EmpleadoEntity empleadoEntity = empleadoRepository.findByNumDoc(dniEmpleado);
        if (empleadoEntity != null){
            EmpleadoEntity empleadoEntity1 = empleadoEntity;
            empleadoEntity1.setEstado(false);
            empleadoEntity1.setDateDelet(new Timestamp(System.currentTimeMillis()));
            empleadoEntity1.setUsuaDelet(Constants.USU_ADMIN);
            empleadoRepository.save(empleadoEntity1);
            EmpleadoDto empleadoDto2 = empleadoMapper.mapToDto(empleadoEntity1);
            return  new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITO_ELIMINAR, Optional.of(empleadoDto2));
        }else {
            return  new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_ERROR, Optional.empty());
        }
    }

    @Override
    public ResponseBase obtnerTodos() {
        Optional<List<EmpleadoEntity>> todosEmpleados =empleadoRepository.findByEstado(true);
        return new ResponseBase(Constants.CODIGO_EXITO,Constants.MENSAJE_EXITO_BUSCAR, todosEmpleados);
    }

    @Override
    public ResponseBase actualizarEmpleado(String numDoc, EmpleadoRequest empleadoRequest) {
        EmpleadoEntity empleadoEntity = empleadoRepository.findByNumDoc(numDoc);
        if (empleadoEntity != null){
                    empleadoEntity.setEdad(empleadoRequest.getEdad());
                    empleadoEntity.setCargo(empleadoRequest.getCargo());
                    empleadoEntity.setSalario(empleadoRequest.getSalario());
                    empleadoEntity.setTelefono(empleadoRequest.getTelefono());
                    empleadoEntity.setCorreo(empleadoRequest.getCorreo());
                    empleadoEntity.setDepartamento(empleadoRequest.getDepartamento());
                empleadoRepository.save(empleadoEntity);
                return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITO_UPDATE, Optional.of(empleadoEntity));
        }else {
            return new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_ERROR, Optional.empty());
        }
    }


    private EmpleadoEntity getEntityRestTemplate(EmpleadoRequest empleadoRequest){
        String url = "https://api.apis.net.pe/v2/reniec/dni?numero=" +empleadoRequest.getNumDoc();
        try {
            ResponseEntity<ResponseReniec> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeader(tokenApi)),
                    ResponseReniec.class
            );

            ResponseReniec responseReniec = response.getBody();
            EmpleadoEntity empleadoEntity = new EmpleadoEntity();

            empleadoEntity.setNombre(responseReniec.getNombres());
            empleadoEntity.setApellido(responseReniec.getApellidoPaterno());
            empleadoEntity.setNumDoc(responseReniec.getNumeroDocumento());
            empleadoEntity.setEdad(empleadoRequest.getEdad());
            empleadoEntity.setCargo(empleadoRequest.getCargo());
            empleadoEntity.setTipoDoc(empleadoRequest.getTipoDoc());
            empleadoEntity.setDepartamento(empleadoRequest.getDepartamento());
            empleadoEntity.setSalario(empleadoRequest.getSalario());
            empleadoEntity.setTelefono(empleadoRequest.getTelefono());
            empleadoEntity.setCorreo(empleadoRequest.getCorreo());
            empleadoEntity.setEstado(empleadoRequest.isEstado());
            empleadoEntity.setDireccion(empleadoRequest.getDireccion());
            empleadoEntity.setUsuaCrea(Constants.USU_ADMIN);
            empleadoEntity.setDateCreate(new Timestamp(System.currentTimeMillis()));
            return empleadoEntity;


        }catch (HttpClientErrorException e){
            System.err.println("ERROR AL CONSUMIR EL API EXTERNA " +e.getStatusCode());
        }
        return null;
    }

    private HttpHeaders createHeader(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ token);
        return headers;
    }






}
