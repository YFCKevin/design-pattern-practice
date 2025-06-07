package com.gurula.course.course.exam;

import com.gurula.course.course.Content;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "exam")
public class Exam extends Content {
    private String question;
}
