package com.gurula.course.course.questionnaire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionnaireRecordRepository extends MongoRepository<QuestionnaireRecord, String> {
}
