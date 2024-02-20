package com.v1lladev.naves.espaciales.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class NaveEspacialRequest {

    @NotBlank(message = "Campo nombre es obligatorio")
    @Size(min = 3, max = 40, message = "Campo nombre tiene que tener entre 3 y 40 caracteres")
    private String nombre;

    @NotBlank(message = "Campo modelo es obligatorio")
    @Size(min = 3, max = 30, message = "Campo modelo tiene que tener entre 3 y 30 caracteres")
    private String modelo;

    @NotBlank(message = "Campo seriePelicula es obligatorio")
    @Size(min = 3, max = 50, message = "Campo seriePelicula tiene que tener entre 3 y 50 caracteres")
    private String seriePelicula;

    @Min(value = 0, message = "El mínimo valor númerico para el campo numTripulantes ES 0")
    @Max(value = 1000, message = "El máximo valor númerico para el campo numTripulantes ES 1000")
    private Integer numTripulantes;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLanzamiento;

    @NotBlank(message = "Campo fabricante es obligatorio")
    @Size(min = 3, max = 50, message = "Campo fabricante tiene que tener entre 3 y 50 caracteres")
    private String fabricante;

    @NotBlank(message = "Campo estado es obligatorio")
    @Size(min = 3, max = 20, message = "Campo estado tiene que tener entre 3 y 20 caracteres")
    private String estado;

    @Size(max = 300, message = "Campo descripcion puede tener máximo 300 caracteres")
    private String descripcion;

}
