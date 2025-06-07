package com.gurula.course.order;

import com.gurula.course.exception.Result;
import com.gurula.course.order.dto.OrderDTO;
import com.gurula.course.order.dto.PaymentDTO;
import com.gurula.course.order.state.OrderState;
import com.gurula.course.payment.dto.PaymentRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final OrderStateFactory orderStateFactory;

    public OrderServiceImpl(OrderDetailRepository orderDetailRepository,
                            OrderRepository orderRepository, OrderStateFactory orderStateFactory) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.orderStateFactory = orderStateFactory;
    }

    @Override
    public OrderDetail pay(PaymentDTO dto) {
        final Optional<OrderDetail> opt = orderDetailRepository.findById(dto.getOrderDetailId());
        if (opt.isPresent()) {
            final OrderDetail orderDetail = opt.get();
            OrderState orderState = orderStateFactory.createState(orderDetail.getOrderStatus());
            orderDetail.setPayment("CREDIT_CARD");
            final Result<String, String> result = orderState.pay(orderDetail, dto);
            return orderDetailRepository.save(orderDetail);
        }
        return null;
    }

    @Override
    public void cancel(String id) {
        final Optional<OrderDetail> opt = orderDetailRepository.findById(id);
        if (opt.isPresent()) {
            final OrderDetail orderDetail = opt.get();
            OrderState orderState = orderStateFactory.createState(orderDetail.getOrderStatus());
            final Result<String, String> result = orderState.cancel(orderDetail);
            orderDetailRepository.save(orderDetail);
        }
    }

    @Override
    public void retryPayment(PaymentDTO dto) {
        final Optional<OrderDetail> opt = orderDetailRepository.findById(dto.getOrderDetailId());
        if (opt.isPresent()) {
            final OrderDetail orderDetail = opt.get();
            final Optional<Order> orderOpt = orderRepository.findById(orderDetail.getOrderId());
            if (orderOpt.isPresent()) {
                final Order order = orderOpt.get();
                final int paymentRetryCount = order.getPaymentRetryCount();

                OrderState currentState = orderStateFactory.createState(orderDetail.getOrderStatus());
                final Result<String, String> result = currentState.retryPayment(orderDetail, paymentRetryCount);
                orderDetailRepository.save(orderDetail);

                OrderState nextState = orderStateFactory.createState(orderDetail.getOrderStatus());
                orderDetail.setPayment("CREDIT_CARD");
                final Result<String, String> nextResult = nextState.pay(orderDetail, dto);

                orderDetailRepository.save(orderDetail);
            }
        }
    }

    @Override
    @Transactional
    public String handleThreePartPaymentResult(PaymentRequestDTO dto) {
        final Optional<OrderDetail> opt = orderDetailRepository.findById(dto.getOrderDetailId());
        if (opt.isPresent()) {
            final OrderDetail orderDetail = opt.get();
            OrderState orderState = orderStateFactory.createState(orderDetail.getOrderStatus());
            if ("1".equals(dto.getRtnCode())) {
                final Result<String, String> result = orderState.onPaymentSuccess(orderDetail, dto);
                orderDetailRepository.save(orderDetail);
                return "付款完成";
            } else {
                final Order order = orderRepository.findById(orderDetail.getOrderId()).get();
                final Result<String, String> result = orderState.onPaymentFailure(orderDetail, order);
                orderDetailRepository.save(orderDetail);
                orderRepository.save(order);

                return "付款失敗";
            }
        } else {
            return "訂單不存在";
        }
    }

    @Override
    @Transactional
    public void create(OrderDTO orderDTO) {
        Order order = new Order();
        order.setMemberId(orderDTO.getMemberId());
        order.setOrderNumber(UUID.randomUUID().toString());
        final Order savedOrder = orderRepository.save(order);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(savedOrder.getId());
        orderDetail.setOrderStatus(OrderStatus.NOT_PAID);
        orderDetail.setAmount(2);
        orderDetail.setCourseName(orderDTO.getCourseName());
        orderDetail.setCoursePrice(orderDTO.getCoursePrice());
        orderDetail.setBuyAt(System.currentTimeMillis());
        orderDetailRepository.save(orderDetail);
    }
}
