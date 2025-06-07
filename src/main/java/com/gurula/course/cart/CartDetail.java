package com.gurula.course.cart;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cart_detail")
public class CartDetail {
    @Id
    private String id;
    private int amount;
    private String courseId;
}
