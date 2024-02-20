package com.v1lladev.naves.espaciales.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "NAVES_ESPACIALES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaveEspacialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "SERIE_PELICULA")
    private String seriePelicula;

    @Column(name = "NUM_TRIPULANTES")
    private Integer numTripulantes;

    @Column(name = "FECHA_LANZAMIENTO")
    private LocalDate fechaLanzamiento;

    @Column(name = "FABRICANTE")
    private String fabricante;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "DESCRIPCION")
    private String descripcion;

}
