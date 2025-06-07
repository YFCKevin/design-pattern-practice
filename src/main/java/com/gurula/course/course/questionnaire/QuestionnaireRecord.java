package com.gurula.course.course.questionnaire;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "questionnaire_record")
public class QuestionnaireRecord {
    @Id
    private String id;
    private String memberId;
    private String courseId;
    private String lessonId;
    private String questionnaireId;
    private Long answerDate;
    private String question;
}
