package com.gurula.course.order.state;

import com.gurula.course.exception.Result;
import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.OrderStatus;
import com.gurula.course.returnRecord.ReturnRecord;

public class RejectReturnState extends OrderState {
    @Override
    public Result<String, String> approveReturn(OrderDetail orderDetail, ReturnRecord returnRecord) {
        orderDetail.setOrderStatus(OrderStatus.RETURNED);
        returnRecord.setApproveReturnAt(System.currentTimeMillis());
        return Result.ok("退貨核准");
    }
}
