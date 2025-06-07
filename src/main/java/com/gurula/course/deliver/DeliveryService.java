package com.gurula.course.deliver;

import com.gurula.course.deliver.dto.ShippingDTO;
import com.gurula.course.order.OrderDetail;

public interface DeliveryService {
    void ship(ShippingDTO dto);

    void complete(String id);
}
