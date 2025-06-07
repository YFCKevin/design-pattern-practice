package com.gurula.course.order.state;

import com.gurula.course.exception.Result;
import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.OrderStatus;
import com.gurula.course.returnRecord.ReturnRecord;

public class ReturnedState extends OrderState{
    @Override
    public Result<String, String> rejectReturn(OrderDetail orderDetail, ReturnRecord returnRecord) {
        orderDetail.setOrderStatus(OrderStatus.REJECT_RETURNED);
        returnRecord.setRejectAt(System.currentTimeMillis());
        return Result.ok("拒絕退貨");
    }
}
