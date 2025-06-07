package com.gurula.course.deliver.strategy;

import com.gurula.course.order.OrderDetail;
import org.springframework.stereotype.Component;

/**
 * 超商取貨付款
 */
@Component("STORE_PICKUP_COD")
public class StorePickupCashOnDeliveryStrategy implements DeliveryStrategy{
    @Override
    public void deliver(OrderDetail orderDetail) {
        // 超商取貨付款 (建立貨到付款單據、通知門市...)
    }
}
