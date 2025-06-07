package com.gurula.course.course.upload;

import com.gurula.course.course.MediaType;
import com.gurula.course.course.dto.ContentDTO;
import com.gurula.course.course.exam.Exam;
import com.gurula.course.course.questionnaire.Questionnaire;
import com.gurula.course.course.questionnaire.QuestionnaireRepository;
import com.gurula.course.exception.Result;
import org.springframework.stereotype.Component;

@Component("QUEST")
public class QuestUploadStrategy implements UploadStrategy{
    private final QuestionnaireRepository questionnaireRepository;

    public QuestUploadStrategy(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    @Override
    public Result<String, String> upload(ContentDTO dto) {
        try {
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setName(dto.getName());
            questionnaire.setType(MediaType.QUEST);
            questionnaire.setDescription(dto.getDescription());
            questionnaire.setQuestion(dto.getQuestion());
            questionnaireRepository.save(questionnaire);
            return Result.ok("儲存成功");
        } catch (Exception e) {
            return Result.err(e.getMessage());
        }
    }
}
