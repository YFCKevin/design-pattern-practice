package com.gurula.course.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gurula.course.deliver.DeliveryType;
import com.gurula.course.order.state.OrderState;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "order_detail")
public class OrderDetail {
    @Id
    private String id;
    private OrderStatus orderStatus;
    private String orderId;
    private int amount;
    private Long buyAt;
    private Long payAt;
    private Long cancelAt;
    private String payment; // 付款方式
    private DeliveryType deliver; // 運送方式
    private String courseName;
    private int coursePrice;
    private String failureReason;
    private Long failedAt;
    private Long completeAt; // 完成時間，紀錄訂單完成的時間戳（毫秒）
}
