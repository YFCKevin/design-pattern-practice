package com.gurula.course.course.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WatchEventRecordRepository extends MongoRepository<WatchEventRecord, String> {
}
