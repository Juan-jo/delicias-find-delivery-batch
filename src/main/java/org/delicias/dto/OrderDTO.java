package org.delicias.dto;

import lombok.Builder;
import org.delicias.common.dto.order.OrderStatus;
import org.locationtech.jts.geom.Point;

import java.time.Instant;
import java.util.Objects;

@Builder
public record OrderDTO(
        Long orderId,
        Integer zoneId,
        OrderStatus status,
        Point deliveryLocation,
        Point restaurantAddress,
        Instant readyForDelivery

) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO order = (OrderDTO) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderId);
    }
}
