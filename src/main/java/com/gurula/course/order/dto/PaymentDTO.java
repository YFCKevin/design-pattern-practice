package com.gurula.course.order.dto;

import com.gurula.course.deliver.DeliveryType;
import lombok.Data;

@Data
public class PaymentDTO {
    private String orderDetailId;
    private DeliveryType deliveryType;
}
