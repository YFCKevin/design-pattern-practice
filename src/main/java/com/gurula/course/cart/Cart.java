package com.gurula.course.cart;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "cart")
public class Cart {
    @Id
    private String id;
    private List<CartDetail> cartDetails = new ArrayList<>();
    private String memberId;
}
