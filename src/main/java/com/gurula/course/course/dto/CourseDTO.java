package com.gurula.course.course.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseDTO {
    private String id;
    private String name;
    private int price;
    private List<LessonDTO> lessons = new ArrayList<>();
}
