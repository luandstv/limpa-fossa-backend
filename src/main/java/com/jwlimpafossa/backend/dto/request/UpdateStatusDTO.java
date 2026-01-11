package com.jwlimpafossa.backend.dto.request;

import com.jwlimpafossa.backend.domain.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusDTO(
        @NotNull(message = "O novo status é obrigatório")
        OrderStatus status
) {}