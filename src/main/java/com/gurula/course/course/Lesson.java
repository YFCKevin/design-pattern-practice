package com.gurula.course.course;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@Document(collection = "lesson")
public class Lesson {
    @Id
    private String id;
    private String name;
    private String courseId;
    private String contentId;
    private List<String> lessonIds = new ArrayList<>();
    private String parentId;
    private long latestDiscussTime;
    private int discussCount;
}
