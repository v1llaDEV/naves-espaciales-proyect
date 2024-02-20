package com.v1lladev.naves.espaciales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NaveEspacialIntegrationTests {

    @LocalServerPort
    private int port;

    String baseUrl = "http://localhost:";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        baseUrl = baseUrl + port;
    }

    @ParameterizedTest(name = "Testeando get naves espaciales paginado: page {0}, size {1}")
    @MethodSource("providedTestUsecases")
    @DisplayName("Testeando getAllNavesEspacialesPaginado OK a través de los parámetros page y size")
    void getAllNavesEspacialesPaginadoOKTest(Integer page, Integer size) throws Exception {

        String path = baseUrl.concat("/api/v1/naves-espaciales").concat("?page="+ page).concat("&size="+ size);

        mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(size));
    }

    @Test
    @DisplayName("Testeando getAllNavesEspacialesByNameContaining OK")
    void getAllNavesEspacialesByNameContainingOKTest() throws Exception {

        String path = baseUrl.concat("/api/v1/naves-espaciales").concat("/search?nombre=estelar");

        mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Testeando getNaveEspacialById OK")
    void getNaveEspacialByIdOKTest() throws Exception {
        Long idNaveEspacialValido = 3L;

        String path = baseUrl.concat("/api/v1/naves-espaciales").concat("/" + idNaveEspacialValido);

        mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", equalTo("Nave de Carga Millennium Falcon")));
    }

    @Test
    @DisplayName("Testeando getNaveEspacialById NOT FOUND")
    void getNaveEspacialByIdNotFoundTest() throws Exception {
        Long idNaveEspacialNoEncontrado = 9999L;

        String path = baseUrl.concat("/api/v1/naves-espaciales").concat("/" + idNaveEspacialNoEncontrado);

        mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", equalTo(404)));
    }

    @Test
    @DisplayName("Testeando createNaveEspacial OK")
    void createNaveEspacialOKTest() throws Exception {
        String requestBody = "{\n" +
                "    \"nombre\": \"test nombre\",\n" +
                "    \"modelo\": \"test modelo\",\n" +
                "    \"seriePelicula\": \"test seriepelicula\",\n" +
                "    \"numTripulantes\": 999,\n" +
                "    \"fechaLanzamiento\": \"2000-01-01\",\n" +
                "    \"fabricante\": \"Test fabricante\",\n" +
                "    \"estado\": \"test estado\",\n" +
                "    \"descripcion\": \"test descripcion\"\n" +
                "}";

        mockMvc.perform(post(baseUrl.concat("/api/v1/naves-espaciales"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", equalTo("test nombre")));
    }

    @Test
    @DisplayName("Testeando updateNaveEspacial OK")
    void updateNaveEspacialOKTest() throws Exception {
        String requestBody = "{\n" +
                "    \"nombre\": \"test nombre modificado\",\n" +
                "    \"modelo\": \"test modelo\",\n" +
                "    \"seriePelicula\": \"test seriepelicula\",\n" +
                "    \"numTripulantes\": 999,\n" +
                "    \"fechaLanzamiento\": \"2000-01-01\",\n" +
                "    \"fabricante\": \"Test fabricante\",\n" +
                "    \"estado\": \"test estado\",\n" +
                "    \"descripcion\": \"test descripcion\"\n" +
                "}";

        Long idNaveEspacialValido = 4L;
        String path = baseUrl.concat("/api/v1/naves-espaciales").concat("/" + idNaveEspacialValido);

        mockMvc.perform(put(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", equalTo("test nombre modificado")));
    }

    @Test
    @DisplayName("Testeando deleteNaveEspacial OK")
    void deleteNaveEspacialOKTest() throws Exception {
        Long idNaveEspacialValido = 3L;
        String path = baseUrl.concat("/api/v1/naves-espaciales").concat(String.valueOf("/"+idNaveEspacialValido));

        mockMvc.perform(delete(path))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    private static Stream<Arguments> providedTestUsecases() {
        return Stream.of(
                Arguments.of(0, 3),
                Arguments.of(1, 2),
                Arguments.of(0, 6)
        );
    }

}
