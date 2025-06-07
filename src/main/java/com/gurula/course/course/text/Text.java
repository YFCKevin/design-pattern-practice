package com.gurula.course.course.text;

import com.gurula.course.course.Content;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "text")
public class Text extends Content {
}
