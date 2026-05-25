package com.tecsup.gestion_pedidos.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.stereotype.Component;

@Aspect @Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.tecsup.gestion_pedidos.service.*.*(..))")
    public void inicio(JoinPoint jp) {
        log.info("▶ Iniciando: {}", jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.tecsup.gestion_pedidos.service.*.*(..))")
    public void fin(JoinPoint jp) {
        log.info("✅ Finalizado: {}", jp.getSignature().getName());
    }
}