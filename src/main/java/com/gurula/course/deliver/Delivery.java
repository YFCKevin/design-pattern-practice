package com.gurula.course.deliver;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "delivery")
public class Delivery {
    @Id
    private String id;
    private String orderNumber;
    private String orderId;
    private String orderDetailId;
    private DeliveryType deliveryType;
    private String courseName;
    private String coursePrice;
    private Long shipAt;
    private Long cancelAt;
    private String cancelReason;
    private String customerAddress;
    private String customerName;
    private String customerId;      // memberId
    private String contactPhone;
}
