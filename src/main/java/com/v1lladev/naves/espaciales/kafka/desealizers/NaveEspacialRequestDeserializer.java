package com.v1lladev.naves.espaciales.kafka.desealizers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class NaveEspacialRequestDeserializer implements Deserializer<NaveEspacialRequest> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configuración opcional, puedes dejarla vacía si no necesitas ninguna configuración adicional
    }

    @Override
    public NaveEspacialRequest deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, NaveEspacialRequest.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al deserializar el mensaje", e);
        }
    }
}
