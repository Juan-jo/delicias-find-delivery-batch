package org.delicias.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.delicias.common.dto.order.OrderStatus;
import org.delicias.dto.OrderDTO;
import org.delicias.models.DeliveryUser;

@ApplicationScoped
public class DeliveryUserRepository implements PanacheRepositoryBase<DeliveryUser, Long> {

    @Inject
    EntityManager em;

    // TODO: try assign with user delivery available
    @Transactional
    public void assignOrder(OrderDTO order) {
        Object result = em.createNativeQuery("""
                SELECT del_assign_nearest_delivery_user_avaibale(:lat, :lon, :order_id, :zone_id)
                """)
                .setParameter("lat", order.restaurantAddress().getY())
                .setParameter("lon", order.restaurantAddress().getX())
                .setParameter("order_id", order.orderId())
                .setParameter("zone_id", order.zoneId())
                .getSingleResult();

        if(result != null) {
            System.out.println("---------- Order #" + order.orderId() + " assigned user delivery " + result);
        }
    }

}
