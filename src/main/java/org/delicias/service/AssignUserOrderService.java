package org.delicias.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.delicias.dto.OrderDTO;
import org.delicias.dto.RespSearchDeliveryDTO;
import org.delicias.models.DeliveryUser;
import org.delicias.repositories.DeliveryUserRepository;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

@ApplicationScoped
public class AssignUserOrderService {

    @Inject
    DeliveryUserRepository deliveryUserRepository;

    private final Integer _15km = 15000;



    public RespSearchDeliveryDTO assign(OrderDTO order) {





        return null;
    }


}


/*
*
*
* import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class DeliverySimulator {

    @Inject
    EntityManager em;

    public void simulate() throws InterruptedException {

        int threads = 20;
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            executor.submit(this::assignOrder);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    @Transactional
    public void assignOrder() {
        Object result = em.createNativeQuery("""
                SELECT assign_nearest_delivery(:lat, :lon)
                """)
            .setParameter("lat", 21.117309)
            .setParameter("lon", -98.427372)
            .getSingleResult();

        System.out.println("Assigned delivery: " + result);
    }
}
* */