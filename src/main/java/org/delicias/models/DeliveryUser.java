package org.delicias.models;

import jakarta.persistence.*;
import lombok.*;
import org.delicias.common.dto.delivery.DeliveryUserStatus;
import org.locationtech.jts.geom.Point;

import java.util.UUID;


@Entity
@Table(name = "delivery_users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryUser {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_user_id_seq")
    @SequenceGenerator(
            name = "delivery_user_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "delivery_uuid")
    private UUID deliveryUUID;

    @Column(name = "zone_id")
    private Integer zoneId;

    @Enumerated(EnumType.STRING)
    private DeliveryUserStatus status;

    @Column(name = "last_position", columnDefinition = "GEOGRAPHY(Point, 4326)")
    private Point lastPosition;
}
