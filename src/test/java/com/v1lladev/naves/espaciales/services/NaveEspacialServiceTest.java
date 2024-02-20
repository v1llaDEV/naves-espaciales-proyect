package com.v1lladev.naves.espaciales.services;

import com.v1lladev.naves.espaciales.dto.exceptions.GeneralResourceNotFoundException;
import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import com.v1lladev.naves.espaciales.dto.responses.NaveEspacialResponse;
import com.v1lladev.naves.espaciales.mappers.NaveEspacialMapper;
import com.v1lladev.naves.espaciales.models.NaveEspacialEntity;
import com.v1lladev.naves.espaciales.repositories.NaveEspacialRepository;
import com.v1lladev.naves.espaciales.services.impl.NaveEspacialServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NaveEspacialServiceTest {

    @Mock
    private NaveEspacialRepository naveEspacialRepository;

    @Mock
    private NaveEspacialMapper naveEspacialMapper;

    @InjectMocks
    private NaveEspacialServiceImpl naveEspacialService;

    @Test
    @DisplayName("NaveEspacialService método getAllNavesEspacialesPaginado OK")
    public void getAllNavesEspacialesPaginadoTest() throws Exception {
        int page = 0, size = 3;

        NaveEspacialEntity naveEspacialEntity = new NaveEspacialEntity();
        List<NaveEspacialEntity> naveEspecialExpected = List.of(naveEspacialEntity, naveEspacialEntity);

        NaveEspacialResponse naveEspacialResponse = new NaveEspacialResponse();
        List<NaveEspacialResponse> naveEspecialResult = List.of(naveEspacialResponse, naveEspacialResponse);

        when(naveEspacialRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(naveEspecialExpected));
        when(naveEspacialMapper.listToResponsePage(any())).thenReturn(new ArrayList<>(naveEspecialResult));

        List<NaveEspacialResponse> allNavesEspaciales = naveEspacialService.getAllNavesEspacialesPaginado(page, size);

        assertThat(allNavesEspaciales.size(), is(equalTo(naveEspecialExpected.size())));

    }

    @Test
    @DisplayName("NaveEspacialService método getAllNavesEspacialesByNameContaining OK")
    public void getAllNavesEspacialesByNameContainingTest() {

        NaveEspacialEntity naveEspacialEntity = new NaveEspacialEntity();
        List<NaveEspacialEntity> naveEspecialExpected = List.of(naveEspacialEntity, naveEspacialEntity);

        NaveEspacialResponse naveEspacialResponse = new NaveEspacialResponse();
        List<NaveEspacialResponse> naveEspecialResult = List.of(naveEspacialResponse, naveEspacialResponse);

        when(naveEspacialRepository.findByNombreContainsIgnoreCase(any(String.class))).thenReturn(new ArrayList<>(naveEspecialExpected));
        when(naveEspacialMapper.listToResponse(any())).thenReturn(new ArrayList<>(naveEspecialResult));

        List<NaveEspacialResponse> allNavesEspaciales = naveEspacialService.getAllNavesEspacialesByNameContaining("test");

        assertThat(allNavesEspaciales.size(), is(equalTo(naveEspecialExpected.size())));
    }

    @Test
    @DisplayName("NaveEspacialService método getNaveEspacialById OK")
    public void getNaveEspacialByIdTest() {

        NaveEspacialEntity naveEspacialEntity = new NaveEspacialEntity();
        NaveEspacialResponse naveEspacialResponse = new NaveEspacialResponse();
        naveEspacialResponse.setNombre("test");
        naveEspacialResponse.setModelo("test");

        when(naveEspacialRepository.findById(any(Long.class))).thenReturn(Optional.of(naveEspacialEntity));
        when(naveEspacialMapper.toResponse(any())).thenReturn(naveEspacialResponse);

        NaveEspacialResponse naveEspacialResult = naveEspacialService.getNaveEspacialById(1L);

        assertThat(naveEspacialResult, is(equalTo(naveEspacialResponse)));
    }

    @Test
    @DisplayName("NaveEspacialService método createNaveEspacial OK")
    public void createNaveEspacialTest() {

        NaveEspacialEntity naveEspacialEntity = new NaveEspacialEntity();
        NaveEspacialRequest naveEspacialRequest = new NaveEspacialRequest();
        NaveEspacialResponse naveEspacialResponse = NaveEspacialResponse.builder()
                .id(1L)
                .nombre("test").modelo("test").seriePelicula("test").numTripulantes(1).fechaLanzamiento(LocalDate.now())
                .fabricante("test").estado("test").descripcion("test").build();

        when(naveEspacialMapper.requestToEntity(any())).thenReturn(naveEspacialEntity);
        when(naveEspacialRepository.save(any(NaveEspacialEntity.class))).thenReturn(naveEspacialEntity);
        when(naveEspacialMapper.toResponse(any())).thenReturn(naveEspacialResponse);

        NaveEspacialResponse naveEspacialResult = naveEspacialService.createNaveEspacial(naveEspacialRequest);

        assertThat(naveEspacialResult, is(equalTo(naveEspacialResponse)));
    }
    @Test
    @DisplayName("NaveEspacialService método updateNaveEspacial OK")
    public void updateNaveEspacialTest() {

        NaveEspacialEntity naveEspacialEntity = new NaveEspacialEntity();
        NaveEspacialRequest naveEspacialRequest = new NaveEspacialRequest();
        NaveEspacialResponse naveEspacialResponse = NaveEspacialResponse.builder()
                .id(1L)
                .nombre("test").modelo("test").seriePelicula("test").numTripulantes(1).fechaLanzamiento(LocalDate.now())
                .fabricante("test").estado("test").descripcion("test").build();

        when(naveEspacialRepository.findById(any(Long.class))).thenReturn(Optional.of(naveEspacialEntity));
        when(naveEspacialMapper.requestToEntity(any())).thenReturn(naveEspacialEntity);
        when(naveEspacialRepository.save(any(NaveEspacialEntity.class))).thenReturn(naveEspacialEntity);
        when(naveEspacialMapper.toResponse(any())).thenReturn(naveEspacialResponse);

        NaveEspacialResponse naveEspacialResult = naveEspacialService.updateNaveEspacial(1L, naveEspacialRequest);

        assertThat(naveEspacialResult, is(equalTo(naveEspacialResponse)));
    }

    @Test
    @DisplayName("NaveEspacialService método deleteNaveEspacial OK")
    public void deleteNaveEspacialTest() {
        when(naveEspacialRepository.findById(any(Long.class))).thenReturn(Optional.of(new NaveEspacialEntity()));

        assertDoesNotThrow(() -> {
            naveEspacialService.deleteNaveEspacial(1L);
        });
    }

    @Test
    @DisplayName("NaveEspacialService método deleteNaveEspacial NOT FOUND")
    public void deleteNaveEspacialNotFoundTest() {
        assertThrows(GeneralResourceNotFoundException.class, () -> naveEspacialService.deleteNaveEspacial(1L));
    }
}