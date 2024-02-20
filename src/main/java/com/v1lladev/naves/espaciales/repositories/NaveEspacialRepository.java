package com.v1lladev.naves.espaciales.repositories;

import com.v1lladev.naves.espaciales.models.NaveEspacialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NaveEspacialRepository extends JpaRepository<NaveEspacialEntity, Long> {

    List<NaveEspacialEntity> findByNombreContainsIgnoreCase(String nombre);
}

