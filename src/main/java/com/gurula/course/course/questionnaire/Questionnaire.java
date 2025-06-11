package com.gurula.course.course.questionnaire;

import com.gurula.course.course.Content;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "questionnaire")
public class Questionnaire extends Content {
    private String question;
}
