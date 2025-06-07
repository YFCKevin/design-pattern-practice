package com.gurula.course.course;

import jakarta.persistence.*;
import lombok.Data;

@Data
public abstract class Content {
    @Id
    private String id;
    private String name;
    private String description;
    private MediaType type;
}
