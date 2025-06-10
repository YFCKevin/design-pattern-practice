package com.gurula.course.course.video;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoErrorRecordRepository extends MongoRepository<VideoErrorRecord, String> {
}
