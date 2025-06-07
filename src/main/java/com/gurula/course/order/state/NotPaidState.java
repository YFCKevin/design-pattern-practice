package com.gurula.course.order.state;

import com.gurula.course.deliver.strategy.DeliveryStrategy;
import com.gurula.course.exception.Result;
import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.OrderStatus;
import com.gurula.course.order.dto.PaymentDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class NotPaidState extends OrderState{
    private final Map<String, DeliveryStrategy> strategyMap;
    public NotPaidState(Map<String, DeliveryStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }
    @Override
    public Result<String, String> pay(OrderDetail orderDetail, PaymentDTO dto) {
        if (orderDetail.getBuyAt() == null || orderDetail.getBuyAt() <= 0) {
            return Result.err("訂單尚未建立（缺少購買時間）");
        }

        if (orderDetail.getCoursePrice() <= 0) {
            return Result.err("訂單金額無效");
        }

        if (StringUtils.isBlank(orderDetail.getPayment())) {
            return Result.err("付款方式尚未指定");
        }

        orderDetail.setOrderStatus(OrderStatus.PROCESSING);
        this.initiateThirdPartyPayment(orderDetail, dto);
        return Result.ok("執行付款流程中");
    }

    @Override
    public Result<String, String> cancel(OrderDetail orderDetail) {
        orderDetail.setOrderStatus(OrderStatus.CANCELLED);
        orderDetail.setCancelAt(System.currentTimeMillis());
        return Result.ok("取消訂單");
    }

    private void initiateThirdPartyPayment(OrderDetail orderDetail, PaymentDTO dto) {
        orderDetail.setPayment("CREDIT_CARD");
        orderDetail.setDeliver(dto.getDeliveryType());
    }

}
