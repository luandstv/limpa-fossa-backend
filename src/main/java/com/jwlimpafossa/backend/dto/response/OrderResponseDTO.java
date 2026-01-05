package com.jwlimpafossa.backend.dto.response;

import com.jwlimpafossa.backend.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponseDTO(
        Long id,
        String customerName,  // Achatamos o objeto: em vez de customer.name, enviamos só o nome
        String customerDocument,
        OrderStatus status,
        Integer distanceMeters,
        BigDecimal price,
        LocalDateTime createdAt
) {
}
