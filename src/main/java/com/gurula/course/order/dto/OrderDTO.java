package com.gurula.course.order.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private String memberId;
    private String courseName;
    private int coursePrice;
}
