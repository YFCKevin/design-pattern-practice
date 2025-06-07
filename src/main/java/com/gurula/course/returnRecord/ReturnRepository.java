package com.gurula.course.returnRecord;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReturnRepository extends MongoRepository<ReturnRecord, String> {
    Optional<ReturnRecord> findByOrderItemId(String id);
}
