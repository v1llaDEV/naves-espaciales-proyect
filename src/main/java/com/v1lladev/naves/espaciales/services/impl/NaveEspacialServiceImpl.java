package com.v1lladev.naves.espaciales.services.impl;

import com.v1lladev.naves.espaciales.constants.ExceptionsMessageErrors;
import com.v1lladev.naves.espaciales.dto.exceptions.GeneralResourceNotFoundException;
import com.v1lladev.naves.espaciales.dto.exceptions.PageParametersInvalidException;
import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import com.v1lladev.naves.espaciales.dto.responses.NaveEspacialResponse;
import com.v1lladev.naves.espaciales.mappers.NaveEspacialMapper;
import com.v1lladev.naves.espaciales.models.NaveEspacialEntity;
import com.v1lladev.naves.espaciales.repositories.NaveEspacialRepository;
import com.v1lladev.naves.espaciales.services.NaveEspacialService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NaveEspacialServiceImpl implements NaveEspacialService {

    private final NaveEspacialRepository naveEspacialRepository;
    private final NaveEspacialMapper naveEspacialMapper;

    @Cacheable("naves-espaciales")
    @Override
    public List<NaveEspacialResponse> getAllNavesEspacialesPaginado(int page, int size) throws PageParametersInvalidException {
        if (page < 0) {
            throw new PageParametersInvalidException("El parámetro page no puede ser menor que 0");
        } else if (size < 1) {
            throw new PageParametersInvalidException("El parámetro size no puede ser menor que 1");
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<NaveEspacialEntity> navesEspacialesResultEntity = naveEspacialRepository.findAll(pageRequest);
        return naveEspacialMapper.listToResponsePage(navesEspacialesResultEntity);
    }

    @Cacheable("naves-espaciales")
    @Override
    public List<NaveEspacialResponse> getAllNavesEspacialesByNameContaining(String nombre) {
        List<NaveEspacialEntity> navesEspacialesResultEntity = naveEspacialRepository.findByNombreContainsIgnoreCase(nombre);
        return naveEspacialMapper.listToResponse(navesEspacialesResultEntity);
    }

    @Override
    public NaveEspacialResponse getNaveEspacialById(Long id) {
        NaveEspacialEntity naveEspacialEntity = naveEspacialRepository.findById(id).orElseThrow(() -> new GeneralResourceNotFoundException
                (String.format(ExceptionsMessageErrors.VALIDATION_NAVE_ID_NO_ENCONTRADA_ERROR, id)));
        return naveEspacialMapper.toResponse(naveEspacialEntity);
    }

    @CacheEvict(cacheNames ="naves-espaciales", allEntries = true)
    @Override
    public NaveEspacialResponse createNaveEspacial(NaveEspacialRequest naveEspacial) {
        NaveEspacialEntity naveEspacialEntity = naveEspacialMapper.requestToEntity(naveEspacial);
        NaveEspacialEntity naveEspacialResult = naveEspacialRepository.save(naveEspacialEntity);
        return naveEspacialMapper.toResponse(naveEspacialResult);
    }

    @CacheEvict(cacheNames ="naves-espaciales", allEntries = true)
    @Override
    public NaveEspacialResponse updateNaveEspacial(Long id, NaveEspacialRequest naveEspacial) {
        getNaveEspacialById(id);
        NaveEspacialEntity naveEspacialEntity = naveEspacialMapper.requestToEntity(naveEspacial);
        naveEspacialEntity.setId(id);
        NaveEspacialEntity naveEspacialResult = naveEspacialRepository.save(naveEspacialEntity);
        return naveEspacialMapper.toResponse(naveEspacialResult);
    }

    @CacheEvict(cacheNames ="naves-espaciales", allEntries = true)
    @Override
    public void deleteNaveEspacial(Long id) {
        getNaveEspacialById(id);
        naveEspacialRepository.deleteById(id);
    }
}
