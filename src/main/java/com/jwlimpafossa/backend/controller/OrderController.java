package com.jwlimpafossa.backend.controller;

import com.jwlimpafossa.backend.dto.CreateOrderDTO;
import com.jwlimpafossa.backend.dto.request.UpdateStatusDTO;
import com.jwlimpafossa.backend.dto.response.OrderResponseDTO;
import com.jwlimpafossa.backend.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO create(@RequestBody @Valid CreateOrderDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public Page<OrderResponseDTO> listAll(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable
            ){
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public OrderResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PatchMapping("/{id}/status")
    public OrderResponseDTO updateStatus(@PathVariable Long id, @RequestBody @Valid UpdateStatusDTO dto) {
        return service.updateStatus(id, dto.status());
    }

}
