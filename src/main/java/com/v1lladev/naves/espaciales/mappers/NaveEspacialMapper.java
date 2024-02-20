package com.v1lladev.naves.espaciales.mappers;

import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import com.v1lladev.naves.espaciales.dto.responses.NaveEspacialResponse;
import com.v1lladev.naves.espaciales.models.NaveEspacialEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NaveEspacialMapper {

    List<NaveEspacialRequest> listToRequest(List<NaveEspacialEntity> naveEspacialEntity);

    List<NaveEspacialResponse> listToResponse(List<NaveEspacialEntity> naveEspacialEntity);
    List<NaveEspacialResponse> listToResponsePage(Page<NaveEspacialEntity> naveEspacialEntity);
    NaveEspacialResponse toResponse(NaveEspacialEntity naveEspacialEntity);

    NaveEspacialEntity requestToEntity(NaveEspacialRequest naveEspacialEntity);
}
