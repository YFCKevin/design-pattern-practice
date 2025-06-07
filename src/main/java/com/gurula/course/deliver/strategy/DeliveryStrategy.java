package com.gurula.course.deliver.strategy;

import com.gurula.course.order.OrderDetail;

public interface DeliveryStrategy {
    void deliver(OrderDetail orderDetail);
}
