package com.jwlimpafossa.backend.mapper;

import com.jwlimpafossa.backend.domain.Order;
import com.jwlimpafossa.backend.dto.CreateOrderDTO;
import com.jwlimpafossa.backend.dto.response.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "customerDocument", source = "customer.document")
    OrderResponseDTO toResponse(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Order toEntity(CreateOrderDTO dto);
}
