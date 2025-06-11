package com.gurula.course.faq;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FAQRepository extends MongoRepository<FAQ, String> {
    Optional<FAQ> findByFaqType(String type);
}
