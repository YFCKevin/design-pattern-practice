package com.gurula.course.order;

import com.gurula.course.order.dto.OrderDTO;
import com.gurula.course.order.dto.PaymentDTO;
import com.gurula.course.payment.dto.PaymentRequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final RestTemplate restTemplate;

    public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.create(orderDTO);
        return ResponseEntity.ok("新增訂單成功");
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payOrder(@RequestBody PaymentDTO dto) {
        final OrderDetail orderDetail = orderService.pay(dto);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:7001/course/payment/callback",
                HttpMethod.POST,
                new HttpEntity<>(
                        new PaymentRequestDTO(orderDetail.getId(), "1", System.currentTimeMillis()),
                        new HttpHeaders()
                ),
                String.class
        );

        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable String id) {
        orderService.cancel(id);
        return ResponseEntity.ok("取消訂單");
    }

    @PostMapping("/retryPayment")
    public ResponseEntity<?> retryPayment(@RequestBody PaymentDTO dto) {
        orderService.retryPayment(dto);
        return ResponseEntity.ok("重新付款成功");
    }
}
