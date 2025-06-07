package com.gurula.course.course;

import com.gurula.course.course.dto.LessonDTO;

import java.util.List;

public interface LessonService {
    void saveAll(List<LessonDTO> lessons);
}
