package com.tecsup.gestion_pedidos.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.stereotype.Component;

@Aspect @Component
public class ErrorAspect {
    private static final Logger log = LoggerFactory.getLogger(ErrorAspect.class);

    @AfterThrowing(pointcut = "execution(* com.tecsup.gestion_pedidos.service.*.*(..))", throwing = "ex")
    public void capturarError(JoinPoint jp, Exception ex) {
        log.error("❌ Error en {}: {}", jp.getSignature().getName(), ex.getMessage());
    }
}