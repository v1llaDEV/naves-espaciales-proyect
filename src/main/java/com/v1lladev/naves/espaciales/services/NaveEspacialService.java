package com.v1lladev.naves.espaciales.services;

import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import com.v1lladev.naves.espaciales.dto.responses.NaveEspacialResponse;

import java.util.List;

public interface NaveEspacialService {

    List<NaveEspacialResponse> getAllNavesEspaciales(int page, int size);
    List<NaveEspacialResponse> getAllNavesEspacialesByNameContaining(String nombre);
    NaveEspacialResponse getNaveEspacialById(Long id);
    NaveEspacialResponse createNaveEspacial(NaveEspacialRequest naveEspacial);
    NaveEspacialResponse updateNaveEspacial(Long id, NaveEspacialRequest naveEspacial);
    void deleteNaveEspacial(Long id);
}
