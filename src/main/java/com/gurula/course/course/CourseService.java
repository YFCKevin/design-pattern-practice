package com.gurula.course.course;

import com.gurula.course.course.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    void save(CourseDTO dto);

    List<CourseDTO> findAll();
}
