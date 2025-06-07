package com.gurula.course.course;

import com.gurula.course.course.dto.LessonDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService{
    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void saveAll(List<LessonDTO> lessons) {
        final List<Lesson> lessonList = lessons.stream().map(lessonDTO -> {
            Lesson lesson = new Lesson();
            lesson.setName(lessonDTO.getName());
            lesson.setContentId(lessonDTO.getContent().getId());
            lesson.setCourseId(lessonDTO.getCourseId());
            lesson.setParentId(lessonDTO.getParentId());
            return lesson;
        }).toList();
        lessonRepository.saveAll(lessonList);
    }
}
