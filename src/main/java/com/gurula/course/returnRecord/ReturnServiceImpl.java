package com.gurula.course.returnRecord;

import com.gurula.course.deliver.Delivery;
import com.gurula.course.deliver.DeliveryRepository;
import com.gurula.course.exception.Result;
import com.gurula.course.order.OrderDetail;
import com.gurula.course.order.OrderDetailRepository;
import com.gurula.course.order.OrderStateFactory;
import com.gurula.course.order.dto.ReturnRequestDTO;
import com.gurula.course.order.state.OrderState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReturnServiceImpl implements ReturnService{
    private final ReturnRepository returnRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderStateFactory orderStateFactory;
    private final DeliveryRepository deliveryRepository;

    public ReturnServiceImpl(ReturnRepository returnRepository, OrderDetailRepository orderDetailRepository, OrderStateFactory orderStateFactory,
                             DeliveryRepository deliveryRepository) {
        this.returnRepository = returnRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderStateFactory = orderStateFactory;
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    @Transactional
    public void applyReturn(ReturnRequestDTO dto) {
        final Optional<OrderDetail> opt = orderDetailRepository.findById(dto.getOrderDetailId());
        if (opt.isPresent()) {
            final OrderDetail orderDetail = opt.get();
            final OrderState orderState = orderStateFactory.createState(orderDetail.getOrderStatus());
            final Delivery delivery = deliveryRepository.findByOrderDetailId(orderDetail.getId()).get();
            final ReturnRecord returnRecord = new ReturnRecord();
            final Result<String, String> result = orderState.applyReturn(orderDetail, returnRecord, dto, delivery);
            orderDetailRepository.save(orderDetail);
            returnRepository.save(returnRecord);
            deliveryRepository.save(delivery);
        }
     }

    @Override
    @Transactional
    public void approveReturn(String id) {
        final Optional<OrderDetail> opt = orderDetailRepository.findById(id);
        if (opt.isPresent()) {
            final OrderDetail orderDetail = opt.get();
            final ReturnRecord returnRecord = returnRepository.findByOrderItemId(orderDetail.getId()).get();
            final OrderState orderState = orderStateFactory.createState(orderDetail.getOrderStatus());
            final Result<String, String> result = orderState.approveReturn(orderDetail, returnRecord);
            orderDetailRepository.save(orderDetail);
            returnRepository.save(returnRecord);
        }
    }

    @Override
    @Transactional
    public void editReason(ReturnRequestDTO dto) {
        final Optional<OrderDetail> opt = orderDetailRepository.findById(dto.getOrderDetailId());
        if (opt.isPresent()) {
            final OrderDetail orderDetail = opt.get();
            final ReturnRecord returnRecord = returnRepository.findByOrderItemId(orderDetail.getId()).get();
            final OrderState orderState = orderStateFactory.createState(orderDetail.getOrderStatus());
            final Result<String, String> result = orderState.applyReturn(orderDetail, returnRecord, dto, new Delivery());
            orderDetailRepository.save(orderDetail);
            returnRepository.save(returnRecord);
        }
    }

    @Override
    @Transactional
    public void rejectReturn(String id) {
        final Optional<OrderDetail> opt = orderDetailRepository.findById(id);
        if (opt.isPresent()) {
            final OrderDetail orderDetail = opt.get();
            final OrderState orderState = orderStateFactory.createState(orderDetail.getOrderStatus());
            final ReturnRecord returnRecord = returnRepository.findByOrderItemId(orderDetail.getId()).get();
            final Result<String, String> result = orderState.rejectReturn(orderDetail, returnRecord);
            orderDetailRepository.save(orderDetail);
            returnRepository.save(returnRecord);
        }
    }
}
