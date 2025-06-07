package com.gurula.course.course.upload;

import com.gurula.course.course.MediaType;
import com.gurula.course.course.dto.ContentDTO;
import com.gurula.course.course.exam.Exam;
import com.gurula.course.course.exam.ExamRepository;
import com.gurula.course.exception.Result;
import org.springframework.stereotype.Component;

@Component("EXAM")
public class ExamUploadStrategy implements UploadStrategy{
    private final ExamRepository examRepository;

    public ExamUploadStrategy(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Result<String, String> upload(ContentDTO dto) {
        try {
            Exam exam = new Exam();
            exam.setName(dto.getName());
            exam.setType(MediaType.EXAM);
            exam.setDescription(dto.getDescription());
            exam.setQuestion(dto.getQuestion());
            examRepository.save(exam);
            return Result.ok("儲存成功");
        } catch (Exception e) {
            return Result.err(e.getMessage());
        }
    }
}
