package com.gurula.course.course.questionnaire;

import com.gurula.course.course.questionnaire.CoR.ResponseHandler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireRecordServiceImpl implements QuestionnaireRecordService{
    private final QuestionnaireRecordRepository questionnaireRecordRepository;
    private final ResponseHandler responseHandler;

    public QuestionnaireRecordServiceImpl(QuestionnaireRecordRepository questionnaireRecordRepository, ResponseHandler responseHandler) {
        this.questionnaireRecordRepository = questionnaireRecordRepository;
        this.responseHandler = responseHandler;
    }

    @Override
    public QuestionnaireRecord save(QuestionnaireRecord record) {
        return questionnaireRecordRepository.save(record);
    }

    @Async
    @Override
    public void response(QuestionnaireRecord record) {
        final String[] answers = record.getAnswer().split(" \\| ");

        StringBuilder responses = new StringBuilder();
        for (String answer : answers) {
            String response = responseHandler.response(answer);
            responses.append(response).append("\n");
        }

        // 再由AI統整
        String finalResponse = responses.toString();

        // 寄信
    }
}
