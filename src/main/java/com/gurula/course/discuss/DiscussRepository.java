package com.gurula.course.discuss;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiscussRepository extends MongoRepository<Discuss, String> {
}
