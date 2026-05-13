package org.delicias.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.delicias.models.OrderReadyForDelivery;

@ApplicationScoped
public class OrderReadyForDeliveryRepository implements PanacheRepositoryBase<OrderReadyForDelivery, Long> {



}
