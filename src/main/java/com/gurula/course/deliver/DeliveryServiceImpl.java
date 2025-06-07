package com.gurula.course.deliver;

import com.gurula.course.deliver.dto.ShippingDTO;
import com.gurula.course.exception.Result;
import com.gurula.course.order.*;
import com.gurula.course.order.state.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService{
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderStateFactory orderStateFactory;

    public DeliveryServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, OrderStateFactory orderStateFactory) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderStateFactory = orderStateFactory;
    }

    @Override
    public void ship(ShippingDTO dto) {
        final Optional<OrderDetail> opt = orderDetailRepository.findById(dto.getOrderDetailId());
        if (opt.isPresent()) {
            final OrderDetail orderDetail = opt.get();
            OrderState orderState = orderStateFactory.createState(orderDetail.getOrderStatus());
            final Order order = orderRepository.findById(orderDetail.getOrderId()).get();
            final Result<String, String> result = orderState.ship(orderDetail, order, dto);
            if (result.isErr()) {
                log.warn("出貨失敗: {}", result.unwrapErr());
            }
            orderDetailRepository.save(orderDetail);
        }
    }

    @Override
    public void complete(String id) {
        final Optional<OrderDetail> opt = orderDetailRepository.findById(id);
        if (opt.isPresent()) {
            final OrderDetail orderDetail = opt.get();
            final OrderState orderState = orderStateFactory.createState(orderDetail.getOrderStatus());
            final Result<String, String> result = orderState.complete(orderDetail);
            orderDetailRepository.save(orderDetail);
        }
    }
}
