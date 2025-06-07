package com.gurula.course.course;

import com.gurula.course.course.dto.CourseDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "course")
public class Course {

    @Id
    private String id;
    private String name;
    private int price;

    public static Course constructCourse (CourseDTO courseDTO){
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setPrice(courseDTO.getPrice());
        return course;
    }
}
