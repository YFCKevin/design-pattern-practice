package com.gurula.course.course.questionnaire;

public interface QuestionnaireRecordService {
    QuestionnaireRecord save(QuestionnaireRecord record);

    void response(QuestionnaireRecord savedRecord);
}
