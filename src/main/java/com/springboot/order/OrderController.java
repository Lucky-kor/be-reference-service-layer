package com.springboot.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v1/orders")
@Validated
public class OrderController {
    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        return new ResponseEntity<>(orderPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        System.out.println("# orderId: " + orderId);

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        System.out.println("# get Orders");

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 커피 주문 정보는 구현해야 할 실습이 없습니다!
}
