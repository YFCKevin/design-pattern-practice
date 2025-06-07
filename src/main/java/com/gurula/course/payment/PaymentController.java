package com.gurula.course.payment;

import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.OrderService;
import com.gurula.course.order.state.OrderState;
import com.gurula.course.payment.dto.PaymentRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final OrderService orderService;

    public PaymentController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/callback")
    public ResponseEntity<?> handleCallback(@RequestBody PaymentRequestDTO dto) {
        String result = orderService.handleThreePartPaymentResult(dto);
        return ResponseEntity.ok(result);
    }
}
