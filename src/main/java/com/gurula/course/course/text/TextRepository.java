package com.gurula.course.course.text;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TextRepository extends MongoRepository<Text, String> {
}
