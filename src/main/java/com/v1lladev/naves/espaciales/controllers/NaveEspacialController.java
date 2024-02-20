package com.v1lladev.naves.espaciales.controllers;

import com.v1lladev.naves.espaciales.dto.exceptions.PageParametersInvalidException;
import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import com.v1lladev.naves.espaciales.dto.responses.ExceptionResponseDto;
import com.v1lladev.naves.espaciales.dto.responses.NaveEspacialResponse;
import com.v1lladev.naves.espaciales.services.NaveEspacialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/naves-espaciales")
@Tag(name = "Naves espaciales", description = "API para la gestión de naves espaciales")
@Validated
public class NaveEspacialController {

    private final NaveEspacialService naveEspacialService;

    @GetMapping
    @Operation(summary = "Obtener todas las naves espaciales usando paginación", description = "Obtener todas las naves espaciales usando paginación")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NaveEspacialResponse.class))))
    @ApiResponse(responseCode = "204", description = "No existen naves espaciales", content = @Content(schema = @Schema(implementation = Void.class)))
    public ResponseEntity<List<NaveEspacialResponse>> getAllNavesEspacialesPaginado(@RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "5") int size) throws PageParametersInvalidException {
        List<NaveEspacialResponse> resultNavesEspaciales = naveEspacialService.getAllNavesEspacialesPaginado(page, size);

        if(resultNavesEspaciales.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resultNavesEspaciales);
    }

    @Operation(summary = "Buscar naves espaciales a través del parámetro nombre", description = "Buscar naves espaciales a través del parámetro nombre")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NaveEspacialResponse.class))))
    @ApiResponse(responseCode = "204", description = "No existen naves espaciales", content = @Content(schema = @Schema(implementation = Void.class)))
    @ApiResponse(responseCode = "400", description = "Error de validación de algún parámetro de la petición", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponseDto.class)))
    @GetMapping("/search")
    public ResponseEntity<List<NaveEspacialResponse>> getAllNavesEspacialesByNameContaining(@RequestParam(required = true) String nombre) {
        List<NaveEspacialResponse> resultNavesEspaciales = naveEspacialService.getAllNavesEspacialesByNameContaining(nombre);

        if(resultNavesEspaciales.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resultNavesEspaciales);

    }

    @Operation(summary = "Buscar nave espacial a través del parámetro id descrito en el path", description = "Buscar nave espacial a través del parámetro id descrito en el path")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NaveEspacialResponse.class)))
    @ApiResponse(responseCode = "400", description = "Error de validación de algún parámetro de la petición", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "La nave espacial no ha sido encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponseDto.class)))
    @GetMapping("/{id}")
    public NaveEspacialResponse getNaveEspacialById(@PathVariable Long id) {
        return naveEspacialService.getNaveEspacialById(id);
    }

    @Operation(summary = "Crear nueva nave espacial", description = "Buscar nave espacial a través del parámetro id descrito en el path")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NaveEspacialResponse.class)))
    @ApiResponse(responseCode = "400", description = "Validación de algún parámetro de la petición", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponseDto.class)))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public NaveEspacialResponse createNaveEspacial(@Valid @RequestBody NaveEspacialRequest naveEspacial) {
        return naveEspacialService.createNaveEspacial(naveEspacial);
    }

    @Operation(summary = "Actualizar nave espacial a través del parámetro id descrito en el path", description = "Buscar nave espacial a través del parámetro id descrito en el path")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NaveEspacialResponse.class)))
    @ApiResponse(responseCode = "400", description = "Error de validación de algún parámetro de la petición", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "La nave espacial no ha sido encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponseDto.class)))
    @PutMapping("/{id}")
    public NaveEspacialResponse updateNaveEspacial(@PathVariable Long id,@Valid @RequestBody NaveEspacialRequest naveEspacial) {
        return naveEspacialService.updateNaveEspacial(id, naveEspacial);
    }

    @Operation(summary = "Eliminar nave espacial a través del parámetro id descrito en el path", description = "Buscar nave espacial a través del parámetro id descrito en el path")
    @ApiResponse(responseCode = "204", description = "OK - No content", content = @Content(schema = @Schema(implementation = Void.class)))
    @ApiResponse(responseCode = "400", description = "Error de validación de algún parámetro de la petición", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "La nave espacial no ha sido encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponseDto.class)))
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNaveEspacial(@PathVariable Long id) {
        naveEspacialService.deleteNaveEspacial(id);
    }

}
