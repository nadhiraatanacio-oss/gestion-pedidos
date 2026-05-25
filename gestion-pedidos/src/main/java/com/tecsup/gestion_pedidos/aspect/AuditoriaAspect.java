package com.tecsup.gestion_pedidos.aspect;

import com.tecsup.gestion_pedidos.entity.AuditoriaLog;
import com.tecsup.gestion_pedidos.repository.AuditoriaLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.Instant;

@Aspect @Component @RequiredArgsConstructor
public class AuditoriaAspect {
    private final AuditoriaLogRepository repo;

    @AfterReturning("execution(* com.tecsup.gestion_pedidos.service.*.*(..))")
    public void auditar(JoinPoint jp) {
        String usuario = "anonimo";
        try {
            usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception ignored) {}

        AuditoriaLog log = new AuditoriaLog();
        log.setAccion("EJECUTADO");
        log.setMetodo(jp.getSignature().getName());
        log.setFecha(Timestamp.from(Instant.now()));
        log.setUsuario(usuario);
        repo.save(log);
    }
}