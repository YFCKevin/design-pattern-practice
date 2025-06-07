package com.gurula.course.deliver.strategy;

import com.gurula.course.deliver.Delivery;
import com.gurula.course.deliver.DeliveryRepository;
import com.gurula.course.order.OrderDetail;
import org.springframework.stereotype.Component;

/**
 * 宅配到府
 */
@Component("HOME")
public class HomeDeliveryStrategy implements DeliveryStrategy{
    private final DeliveryRepository deliveryRepository;

    public HomeDeliveryStrategy(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public void deliver(OrderDetail orderDetail) {
        // 處理宅配邏輯 (建立宅配單、安排物流...)
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderDetail.getOrderId());
        delivery.setOrderDetailId(orderDetail.getId());
        delivery.setShipAt(System.currentTimeMillis());
        delivery.setDeliveryType(orderDetail.getDeliver());
        deliveryRepository.save(delivery);
    }
}
