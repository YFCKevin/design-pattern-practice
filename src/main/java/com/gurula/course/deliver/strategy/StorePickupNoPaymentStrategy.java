package com.gurula.course.deliver.strategy;

import com.gurula.course.order.OrderDetail;
import org.springframework.stereotype.Component;

/**
 * 超商取貨不付款
 */
@Component("STORE_PICKUP_NO_PAYMENT")
public class StorePickupNoPaymentStrategy implements DeliveryStrategy{
    @Override
    public void deliver(OrderDetail orderDetail) {
        // 超商取貨不付款邏輯 (通知門市、生成取貨碼...)
    }
}
