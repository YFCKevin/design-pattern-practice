package com.gurula.course.order.state;

import com.gurula.course.deliver.Delivery;
import com.gurula.course.deliver.dto.ShippingDTO;
import com.gurula.course.exception.Result;
import com.gurula.course.order.Order;
import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.dto.PaymentDTO;
import com.gurula.course.order.dto.ReturnRequestDTO;
import com.gurula.course.payment.dto.PaymentRequestDTO;
import com.gurula.course.returnRecord.ReturnRecord;

public class OrderState {
    public Result<String, String> retryPayment(OrderDetail orderDetail, int paymentRetryCount) {
        return Result.err("不允許使用者重試付款");
    }

    public Result<String, String> pay(OrderDetail orderDetail, PaymentDTO dto) {
        return Result.err("該狀態不允許付款");
    }

    public Result<String, String> onPaymentSuccess(OrderDetail orderDetail, PaymentRequestDTO dto) {
        return Result.err("該狀態不允許付款成功");
    }

    public Result<String, String> onPaymentFailure(OrderDetail orderDetail, Order order) {
        return Result.err("該狀態不允許付款失敗");
    }

    public Result<String, String> cancel(OrderDetail orderDetail) {
        return Result.err("該狀態不允許取消訂單");
    }

    public Result<String, String> ship(OrderDetail orderDetail, Order order, ShippingDTO dto) {
        return Result.err("該狀態不允許出貨");
    }

    public Result<String, String> applyReturn(OrderDetail orderDetail, ReturnRecord returnRecord, ReturnRequestDTO dto, Delivery delivery) {
        return Result.err("該狀態不允許申請退貨");
    }

    public Result<String, String> approveReturn(OrderDetail orderDetail, ReturnRecord returnRecord) {
        return Result.err("該狀態不允許同意退貨");
    }

    public Result<String, String> complete(OrderDetail orderDetail) {
        return Result.err("該狀態不允許完成");
    }

    public Result<String, String> rejectReturn(OrderDetail orderDetail, ReturnRecord returnRecord) {
        return Result.err("該狀態不允許拒絕退貨");
    }
}
