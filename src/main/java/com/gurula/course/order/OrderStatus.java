package com.gurula.course.order;

import com.gurula.course.order.state.*;

public enum OrderStatus {
    NOT_PAID,
    PROCESSING,
    PAID,
    SHIPPED,
    RETURN_REQUESTED,
    REJECT_RETURNED,
    RETURNED,
    CANCELLED,
    COMPLETED,
    FAILED;
}
