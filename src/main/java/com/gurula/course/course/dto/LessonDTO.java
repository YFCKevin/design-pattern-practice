package com.gurula.course.course.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LessonDTO {
    private String id;
    private String name;
    private String courseId;
    private ContentDTO content;
    private List<LessonDTO> lessons = new ArrayList<>();
    private String parentId;
    private long latestDiscussTime;
    private int discussCount;
}
