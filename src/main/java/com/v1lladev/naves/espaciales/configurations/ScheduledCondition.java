package com.v1lladev.naves.espaciales.configurations;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
@Configuration
public class ScheduledCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String propertyHabilitada = context.getEnvironment().getProperty("habilitar.topic.kafka");
        boolean booleanHabilitada = Boolean.parseBoolean(propertyHabilitada);
        log.info("La property habilitar.topic.kafka está en estado {}", booleanHabilitada);
        return booleanHabilitada;
    }
}
