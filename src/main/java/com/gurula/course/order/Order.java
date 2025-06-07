package com.gurula.course.order;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "order")
public class Order {
    @Id
    private String id;
    private String orderNumber;
    private String memberId;
    private int paymentRetryCount = 0;
}
