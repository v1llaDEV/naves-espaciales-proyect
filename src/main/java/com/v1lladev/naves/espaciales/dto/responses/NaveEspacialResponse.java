package com.v1lladev.naves.espaciales.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
