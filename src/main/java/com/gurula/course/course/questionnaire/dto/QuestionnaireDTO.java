package com.gurula.course.course.questionnaire.dto;

import lombok.Data;

@Data
public class QuestionnaireDTO {
    private String id;
    private String answer;
    private String memberId;
    private String courseId;
    private String lessonId;
}
