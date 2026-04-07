package org.delicias.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RespSearchDeliveryDTO(
        boolean found,
        DeliveryUser deliveryUser
)
{
    @Builder
    public record DeliveryUser(
            Long id,
            UUID deliveryUUID
    ){}
}
