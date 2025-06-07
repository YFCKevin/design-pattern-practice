package com.gurula.course.order.state;

import com.gurula.course.deliver.strategy.DeliveryStrategy;
import com.gurula.course.exception.Result;
import com.gurula.course.order.Order;
import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.OrderStatus;
import com.gurula.course.payment.dto.PaymentRequestDTO;

import java.util.Map;

public class ProcessingState extends OrderState {
    private final Map<String, DeliveryStrategy> strategyMap;
    public ProcessingState(Map<String, DeliveryStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    @Override
    public Result<String, String> onPaymentSuccess(OrderDetail orderDetail, PaymentRequestDTO dto) {
        orderDetail.setOrderStatus(OrderStatus.PAID);
        orderDetail.setPayAt(dto.getPayAt());
        return Result.ok("付款成功");
    }

    @Override
    public Result<String, String> onPaymentFailure(OrderDetail orderDetail, Order order) {
        orderDetail.setOrderStatus(OrderStatus.FAILED);
        orderDetail.setFailedAt(System.currentTimeMillis());
        orderDetail.setFailureReason("付款失敗");
        // 重試付款次數 +1
        order.setPaymentRetryCount(order.getPaymentRetryCount() + 1);
        return Result.ok("付款失敗");
    }
}
