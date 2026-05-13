package org.delicias.models;

import jakarta.persistence.*;
import lombok.Getter;
import org.delicias.common.dto.order.OrderStatus;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;

import java.time.Instant;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ready_orders_for_delivery")
@Getter
public class OrderReadyForDelivery {

    @Id
    @Column(name = "id")
    private Long orderId;

    @Column(name = "zone_id")
    private Integer zoneId;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", columnDefinition = "pos_order_status")
    private OrderStatus status;

    @Column(name = "delivery_location", columnDefinition = "GEOGRAPHY(Point, 4326)")
    private Point deliveryLocation;

    @Column(name = "restaurant_location", columnDefinition = "GEOGRAPHY(Point, 4326)")
    private Point restaurantLocation;

    @Column(name = "ready_for_delivery_at")
    private Instant readyForDeliveryAt;

    @Column(name = "ordered_at")
    private Instant orderedAt;
}
