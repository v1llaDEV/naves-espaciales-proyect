package com.v1lladev.naves.espaciales.controllers;

import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import com.v1lladev.naves.espaciales.dto.responses.NaveEspacialResponse;
import com.v1lladev.naves.espaciales.services.NaveEspacialService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/naves-espaciales")
@Validated
public class NaveEspacialController {

    private final NaveEspacialService naveEspacialService;

    @GetMapping
    public List<NaveEspacialResponse> getAllNavesEspaciales() {
        return naveEspacialService.getAllNavesEspaciales();
    }

    @GetMapping("search")
    public List<NaveEspacialResponse> getAllNavesEspacialesByNameContaining(@RequestParam(required = true) String nombre) {
        return naveEspacialService.getAllNavesEspacialesByNameContaining(nombre);
    }

    @GetMapping("/{id}")
    public NaveEspacialResponse getNaveEspacialById(@PathVariable Long id) {
        return naveEspacialService.getNaveEspacialById(id);
    }

    @PostMapping
    public NaveEspacialResponse createNaveEspacial(@Valid @RequestBody NaveEspacialRequest naveEspacial) {
        return naveEspacialService.createNaveEspacial(naveEspacial);
    }

    @PutMapping("/{id}")
    public NaveEspacialResponse updateNaveEspacial(@PathVariable Long id,@Valid @RequestBody NaveEspacialRequest naveEspacial) {
        return naveEspacialService.updateNaveEspacial(id, naveEspacial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNaveEspacial(@PathVariable Long id) {
        naveEspacialService.deleteNaveEspacial(id);
        return ResponseEntity.noContent().build();
    }

}
