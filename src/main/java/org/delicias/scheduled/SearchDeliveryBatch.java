package org.delicias.scheduled;

import io.quarkus.scheduler.Scheduled;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.delicias.dto.OrderDTO;
import org.delicias.repositories.OrderReadyForDeliveryRepository;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

@ApplicationScoped
public class SearchDeliveryBatch {

    private static final Logger LOG = Logger.getLogger(SearchDeliveryBatch.class);

    private final BlockingQueue<OrderDTO> orderQueue = new LinkedBlockingQueue<>();
    private final Set<Long> inQueueSet = ConcurrentHashMap.newKeySet();

    @Inject
    OrderReadyForDeliveryRepository orderReadyForDeliveryRepository;

    @Inject
    ExecutorService consumerExecutor;

    // Se ejecuta cada 10 segundos
    @Scheduled(every = "5s")
    void producer() {

        List<OrderDTO> orders = orderReadyForDeliveryRepository.listAll().stream()
                .map(it-> OrderDTO.builder()
                        .orderId(it.getOrderId())
                        .status(it.getStatus())
                        .deliveryLocation(it.getDeliveryLocation())
                        .restaurantAddress(it.getRestaurantAddress())
                        .build())
                .toList();

        if(orders.isEmpty()) {
            LOG.info("--- Not Found Orders");
            return;
        }

        LOG.info("--- Add Order To BlockingQueue");

        for (OrderDTO order: orders) {
            boolean addedToSet = inQueueSet.add(order.orderId());
            if (!addedToSet) {
                continue;
            }

            boolean added = orderQueue.offer(order);

            if (!added) {
                inQueueSet.remove(order.orderId());
            }
        }
    }



    @PostConstruct
    public void startConsumers() {

        int THREADS = 4;

        LOG.info("--- Start Consumer Orders BlockingQueue");

        for (int i = 0; i < THREADS; i++) {

            consumerExecutor.submit(() -> {

                while (true) {

                    OrderDTO order = null;

                    try {
                        // Espera hasta que haya una orden en la cola
                        order = orderQueue.take();

                        //log.info("START [{}] Order {}", LocalDateTime.now().format(fmt), order.getId());


                    } catch (Exception e) {
                        System.err.println("Error processing order: " + e.getMessage());

                    } finally {
                        System.out.println("Consumer size " + (long) orderQueue.size());

                        // Eliminar del set para permitir reprocesamiento futuro
                        if (order != null) {
                            inQueueSet.remove(order.orderId());
                        }
                    }
                }
            });
        }
    }
}
