package com.gurula.course.order.state;

import com.gurula.course.deliver.strategy.DeliveryStrategy;
import com.gurula.course.exception.Result;
import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.OrderStatus;

import java.util.Map;

public class FailedState extends OrderState{
    private final Map<String, DeliveryStrategy> strategyMap;
    public FailedState(Map<String, DeliveryStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }
    @Override
    public Result<String, String> retryPayment(OrderDetail orderDetail, int paymentRetryCount) {
        if (paymentRetryCount > 3) {
            return Result.err("已超過重試付款次數");
        }

        orderDetail.setOrderStatus(OrderStatus.NOT_PAID);
        return Result.ok("重新開始進付款流程");
    }
}
