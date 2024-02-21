package com.v1lladev.naves.espaciales.kafka.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


public class NaveEspacialRequestSerializer implements Serializer<NaveEspacialRequest> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configuración opcional, puedes dejarla vacía si no necesitas ninguna configuración adicional
    }

    @Override
    public byte[] serialize(String topic, NaveEspacialRequest data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error al serializar el mensaje", e);
        }
    }

    @Override
    public void close() {
        // Lógica para cerrar recursos si es necesario
    }
}
