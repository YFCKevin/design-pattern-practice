package com.gurula.course.course.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRecordRepository extends MongoRepository<ExamRecord, String> {
}
