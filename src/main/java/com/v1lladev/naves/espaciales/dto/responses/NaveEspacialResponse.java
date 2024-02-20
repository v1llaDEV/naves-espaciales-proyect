package com.v1lladev.naves.espaciales.dto.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NaveEspacialResponse {

    private Long id;
    private String nombre;
    private String modelo;
    private String seriePelicula;
    private Integer numTripulantes;
    private LocalDate fechaLanzamiento;
    private String fabricante;
    private String estado;
    private String descripcion;

}
