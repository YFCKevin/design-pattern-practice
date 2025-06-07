package com.gurula.course.deliver.dto;

import com.gurula.course.deliver.DeliveryType;
import lombok.Data;

@Data
public class ShippingDTO {
    private String orderDetailId;
    private DeliveryType deliveryType;
}
