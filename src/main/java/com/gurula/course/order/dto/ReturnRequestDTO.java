package com.gurula.course.order.dto;

import lombok.Data;

@Data
public class ReturnRequestDTO {
    private String orderDetailId;
    private String reason;
}
