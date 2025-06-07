package com.gurula.course.order.state;

import com.gurula.course.deliver.Delivery;
import com.gurula.course.exception.Result;
import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.OrderStatus;
import com.gurula.course.order.dto.ReturnRequestDTO;
import com.gurula.course.returnRecord.ReturnRecord;

public class ApplyReturnState extends OrderState{
    @Override
    public Result<String, String> approveReturn(OrderDetail orderDetail, ReturnRecord returnRecord) {
        orderDetail.setOrderStatus(OrderStatus.RETURNED);
        returnRecord.setApproveReturnAt(System.currentTimeMillis());
        return Result.ok("退貨核准");
    }

    @Override
    public Result<String, String> applyReturn(OrderDetail orderDetail, ReturnRecord returnRecord, ReturnRequestDTO dto, Delivery delivery) {
        if ((System.currentTimeMillis() - orderDetail.getBuyAt()) > 7L * 24 * 60 * 60 * 1000) {
            return Result.err("超過7天鑑賞期不可退貨");
        }
        orderDetail.setOrderStatus(OrderStatus.RETURN_REQUESTED);

        returnRecord.setReason(dto.getReason());
        return Result.ok("客戶申請退貨");
    }

    @Override
    public Result<String, String> rejectReturn(OrderDetail orderDetail, ReturnRecord returnRecord) {
        orderDetail.setOrderStatus(OrderStatus.REJECT_RETURNED);
        returnRecord.setRejectAt(System.currentTimeMillis());
        return Result.ok("拒絕退貨");
    }
}
