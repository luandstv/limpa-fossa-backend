package com.jwlimpafossa.backend.service;

import com.jwlimpafossa.backend.domain.Customer;
import com.jwlimpafossa.backend.domain.Order;
import com.jwlimpafossa.backend.domain.enums.OrderStatus;
import com.jwlimpafossa.backend.dto.CreateOrderDTO;
import com.jwlimpafossa.backend.dto.response.OrderResponseDTO;
import com.jwlimpafossa.backend.exception.ResourceNotFoundException;
import com.jwlimpafossa.backend.mapper.OrderMapper;
import com.jwlimpafossa.backend.repository.CustomerRepository;
import com.jwlimpafossa.backend.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    // TODO: No futuro, mover estes valores para uma tabela de 'ServiceConfig' no banco de dados
    private static final BigDecimal BASE_PRICE = new BigDecimal("150.00");
    private static final Integer FRANCHISE_METERS = 50;
    private static final BigDecimal PRICE_PER_BLOCK = new BigDecimal("50.00"); // 50 reais
    private static final Integer BLOCK_SIZE = 10; // a cada 10 metros

    @Transactional
    public OrderResponseDTO create(CreateOrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.costumerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + orderDTO.costumerId()));

        Order order = orderMapper.toEntity(orderDTO);

        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        order.setPrice(calculatePrice(orderDTO.distanceMeters()));

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
    }

    private BigDecimal calculatePrice(Integer distance) {
       if (distance <= FRANCHISE_METERS) {
           return BASE_PRICE;
       }

       int extraMeters = distance - FRANCHISE_METERS;

       double blocksNeeded = Math.ceil((double) extraMeters / BLOCK_SIZE);

       BigDecimal extraCost = PRICE_PER_BLOCK.multiply(BigDecimal.valueOf(blocksNeeded));

        return BASE_PRICE.add(extraCost);
    }
}
