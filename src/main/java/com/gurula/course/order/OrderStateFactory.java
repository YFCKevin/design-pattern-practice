package com.gurula.course.order;

import com.gurula.course.deliver.strategy.DeliveryStrategy;
import com.gurula.course.order.state.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderStateFactory {
    private final Map<String, DeliveryStrategy> strategyMap;

    public OrderStateFactory(Map<String, DeliveryStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public OrderState createState(OrderStatus orderStatus) {
        return switch (orderStatus) {
            case NOT_PAID -> new NotPaidState(strategyMap);
            case PROCESSING -> new ProcessingState(strategyMap);
            case PAID -> new PaidState(strategyMap);
            case SHIPPED -> new ShippedState();
            case RETURN_REQUESTED -> new ApplyReturnState();
            case RETURNED -> new ReturnedState();
            case COMPLETED -> new CompleteState();
            case REJECT_RETURNED -> new RejectReturnState();
            case FAILED -> new FailedState(strategyMap);
            case CANCELLED -> new CancelState();
            default -> throw new UnsupportedOperationException("Unknown state");
        };
    }
}
