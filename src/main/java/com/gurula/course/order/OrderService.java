package com.gurula.course.order;

import com.gurula.course.order.dto.OrderDTO;
import com.gurula.course.order.dto.PaymentDTO;
import com.gurula.course.payment.dto.PaymentRequestDTO;

public interface OrderService {
    OrderDetail pay(PaymentDTO dto);

    void cancel(String id);

    void retryPayment(PaymentDTO dto);

    String handleThreePartPaymentResult(PaymentRequestDTO dto);

    void create(OrderDTO orderDTO);
}
