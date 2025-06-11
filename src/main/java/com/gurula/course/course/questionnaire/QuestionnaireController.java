package com.gurula.course.course.questionnaire;

import com.gurula.course.course.questionnaire.dto.QuestionnaireDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quest")
public class QuestionnaireController {
    private final QuestionnaireRecordService questionnaireRecordService;

    public QuestionnaireController(QuestionnaireRecordService questionnaireRecordService) {
        this.questionnaireRecordService = questionnaireRecordService;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submit (@RequestBody QuestionnaireDTO dto){
        QuestionnaireRecord record = new QuestionnaireRecord();
        record.setQuestionnaireId(dto.getId());
        record.setAnswerDate(System.currentTimeMillis());
        record.setCourseId(dto.getCourseId());
        record.setLessonId(dto.getLessonId());
        record.setMemberId(dto.getMemberId());
        record.setAnswer(dto.getAnswer());
        QuestionnaireRecord savedRecord = questionnaireRecordService.save(record);

        questionnaireRecordService.response(savedRecord);

        return ResponseEntity.ok("完成問卷填寫");
    }

}
