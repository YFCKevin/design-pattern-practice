package com.gurula.course.course.dto;

import com.gurula.course.course.MediaType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ContentDTO {
    private String id;
    private String name;
    private String description;
    private String path;
    private MediaType type;
    private String question;
    private int size;
    private MultipartFile file;
}
