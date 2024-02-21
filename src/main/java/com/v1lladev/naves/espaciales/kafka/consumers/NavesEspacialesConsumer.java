package com.v1lladev.naves.espaciales.kafka.consumers;


import com.v1lladev.naves.espaciales.configurations.ScheduledCondition;
import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import com.v1lladev.naves.espaciales.services.NaveEspacialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Conditional(ScheduledCondition.class)
@Slf4j
@Component
public class NavesEspacialesConsumer {

    @Autowired
    private NaveEspacialService naveEspacialService;

    @KafkaListener(topics ="nave-espacial-topic", groupId ="consumer", containerFactory = "kafkaListenerContainerFactory")
    public void listen(NaveEspacialRequest naveEspacialRequest) {
        log.info("Recibiendo nave espacial: {}", naveEspacialRequest);
        naveEspacialService.createNaveEspacial(naveEspacialRequest);
    }

}
