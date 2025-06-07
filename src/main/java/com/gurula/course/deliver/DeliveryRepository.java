package com.gurula.course.deliver;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    Optional<Delivery> findByOrderDetailId(String orderDetailId);
}
