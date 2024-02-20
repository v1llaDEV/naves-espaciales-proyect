package com.v1lladev.naves.espaciales.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class NegativeIdLoggingAspect {

    @Before("execution(* com.v1lladev.naves.espaciales.services.impl.NaveEspacialServiceImpl.getNaveEspacialById(..)) && args(..)")
    public void logNegativeIdInGetbyIdMethod(JoinPoint joinPoint) {
        checkIfIdIsNegative(joinPoint);
    }

    @Before("execution(* com.v1lladev.naves.espaciales.services.impl.NaveEspacialServiceImpl.updateNaveEspacial(..)) && args(..)")
    public void logNegativeIdInUpdateMethod(JoinPoint joinPoint) {
        checkIfIdIsNegative(joinPoint);
    }

    @Before("execution(* com.v1lladev.naves.espaciales.services.impl.NaveEspacialServiceImpl.deleteNaveEspacial(..)) && args(..)")
    public void logNegativeIdInDeleteMethod(JoinPoint joinPoint) {
        checkIfIdIsNegative(joinPoint);
    }

    private void checkIfIdIsNegative(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Long id) {
                if (id < 0) {
                    log.warn("En getNaveEspacialById " + "se solicitó una nave espacial con un ID negativo: {}", id);
                }
            }
        }
    }
}
