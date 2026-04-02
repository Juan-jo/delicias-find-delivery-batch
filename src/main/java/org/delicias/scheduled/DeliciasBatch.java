package org.delicias.scheduled;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import java.time.Instant;

@ApplicationScoped
public class DeliciasBatch {

    private static final Logger LOG = Logger.getLogger(DeliciasBatch.class);

    // Se ejecuta cada 10 segundos
    @Scheduled(every = "10s")
    void ejecutarTarea() {
        LOG.info("Ejecutando actividad batch a las: " + Instant.now());

        // Aquí va tu lógica (procesar DB, llamar API, etc.)
        realizarActividad();
    }

    private void realizarActividad() {
        // Tu lógica de negocio
    }
}
