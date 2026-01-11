package com.jwlimpafossa.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateOrderDTO(
        @NotNull(message = "O ID do cliente é obrigatório")
        Long customerId,

        @NotNull(message = "A distância é obrigatória")
        @Min(value = 1 , message = "A distância deve ser maior que 0")
        Integer distanceMeters,

        String observations
) {}
