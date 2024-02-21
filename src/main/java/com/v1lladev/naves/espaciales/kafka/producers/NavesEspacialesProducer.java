package com.v1lladev.naves.espaciales.kafka.producers;

import com.github.javafaker.Faker;
import com.v1lladev.naves.espaciales.configurations.ScheduledCondition;
import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Conditional(ScheduledCondition.class)
@Slf4j
@Component
public class NavesEspacialesProducer {

    @Autowired
    private Faker faker;

    @Autowired
    private KafkaTemplate<String, NaveEspacialRequest> kafkaTemplate;

    @Scheduled(fixedRate = 3000)
    public void creatingRandomNavesEspaciales() {

        String nombre = faker.name().title();
        String subNombre = nombre.substring(0, Math.min(40, nombre.length()));
        String modelo = faker.funnyName().name();
        String subModelo = modelo.substring(0, Math.min(50, modelo.length()));
        String seriePelicula = faker.name().title();
        String subSeriePelicula = seriePelicula.substring(0, Math.min(50, seriePelicula.length()));
        String fabricante = faker.name().title();
        String subFabricante = fabricante.substring(0, Math.min(50, fabricante.length()));
        String estado = faker.name().title();
        String subEstado = estado.substring(0, Math.min(20, estado.length()));
        String descripcion = faker.shakespeare().hamletQuote();
        String subDescripcion = descripcion.substring(0, Math.min(300, descripcion.length()));

        NaveEspacialRequest naveEspacialRequest = NaveEspacialRequest.builder()
                .nombre(subNombre)
                .modelo(subModelo)
                .seriePelicula(subSeriePelicula)
                .numTripulantes(faker.number().numberBetween(0, 1000))
                .fabricante(subFabricante)
                .estado(subEstado)
                .descripcion(subDescripcion)
                .build();

        CompletableFuture<SendResult<String, NaveEspacialRequest>> future = kafkaTemplate.send("nave-espacial-topic",
                UUID.randomUUID().toString(), naveEspacialRequest);
        future.whenComplete( (result, exception) -> {
           log.info("El productor ha enviado el mensaje con id: " + naveEspacialRequest);
        });

    }


}
