package io.github.thatyane.sagachoreography.order.application.controller;

import io.github.thatyane.sagachoreography.commons.application.dto.OrderRequest;
import io.github.thatyane.sagachoreography.order.domain.entity.PurchaseOrder;
import io.github.thatyane.sagachoreography.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<PurchaseOrder> create(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.create(orderRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

}
