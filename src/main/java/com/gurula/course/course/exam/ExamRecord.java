package com.gurula.course.course.exam;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "exam_record")
public class ExamRecord {
    @Id
    private String id;
    private String memberId;
    private String courseId;
    private String lessonId;
    private String examId;
    private Long answerDate;
    private String question;
}
