package com.gurula.course.order.state;

import com.gurula.course.deliver.Delivery;
import com.gurula.course.deliver.dto.ShippingDTO;
import com.gurula.course.deliver.strategy.DeliveryStrategy;
import com.gurula.course.exception.Result;
import com.gurula.course.order.Order;
import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.OrderStatus;
import com.gurula.course.order.dto.ReturnRequestDTO;
import com.gurula.course.returnRecord.ReturnRecord;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;


public class PaidState extends OrderState{
    private final Map<String, DeliveryStrategy> strategyMap;
    public PaidState(Map<String, DeliveryStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    @Override
    public Result<String, String> ship(OrderDetail orderDetail, Order order, ShippingDTO dto) {
        if (StringUtils.isBlank(orderDetail.getDeliver().name())) {
            return Result.err("尚未選擇取貨方式");
        } else {
            final DeliveryStrategy strategy = strategyMap.get(orderDetail.getDeliver().name());
            orderDetail.setDeliver(dto.getDeliveryType());
            strategy.deliver(orderDetail);
        }

        orderDetail.setOrderStatus(OrderStatus.SHIPPED);

        return Result.ok("排定出貨");
    }

    @Override
    public Result<String, String> applyReturn(OrderDetail orderDetail, ReturnRecord returnRecord, ReturnRequestDTO dto, Delivery delivery) {
        if (orderDetail.getPayAt() == null || orderDetail.getPayAt() <= 0) {
            return Result.err("尚未付款不可退貨");
        }
        if (orderDetail.getCoursePrice() <= 0) {
            return Result.err("贈品不可退貨");
        }
        if ((System.currentTimeMillis() - orderDetail.getBuyAt()) > 7L * 24 * 60 * 60 * 1000) {
            return Result.err("超過7天鑑賞期不可退貨");
        }
        orderDetail.setOrderStatus(OrderStatus.RETURN_REQUESTED);

        returnRecord.setOrderId(orderDetail.getOrderId());
        returnRecord.setOrderItemId(orderDetail.getId());
        returnRecord.setApplyReturnAt(System.currentTimeMillis());
        returnRecord.setReason(dto.getReason());
        return Result.ok("客戶申請退貨");
    }
}
