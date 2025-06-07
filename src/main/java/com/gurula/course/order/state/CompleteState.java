package com.gurula.course.order.state;


import com.gurula.course.deliver.Delivery;
import com.gurula.course.exception.Result;
import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.OrderStatus;
import com.gurula.course.order.dto.ReturnRequestDTO;
import com.gurula.course.returnRecord.ReturnRecord;

public class CompleteState extends OrderState{
    @Override
    public Result<String, String> applyReturn(OrderDetail orderDetail, ReturnRecord returnRecord, ReturnRequestDTO dto, Delivery delivery) {
        if (orderDetail.getPayAt() == null || orderDetail.getPayAt() <= 0) {
            throw new IllegalStateException("尚未付款不可退貨");
        }
        if (orderDetail.getCoursePrice() <= 0) {
            throw new IllegalStateException("贈品不可退貨");
        }
        if ((System.currentTimeMillis() - orderDetail.getBuyAt()) > 7L * 24 * 60 * 60 * 1000) {
            throw new IllegalStateException("超過7天鑑賞期不可退貨");
        }
        orderDetail.setOrderStatus(OrderStatus.RETURN_REQUESTED);

        returnRecord.setOrderId(orderDetail.getOrderId());
        returnRecord.setOrderItemId(orderDetail.getId());
        returnRecord.setApplyReturnAt(System.currentTimeMillis());
        returnRecord.setReason(dto.getReason());

        return Result.ok("客戶申請退貨");
    }
}
