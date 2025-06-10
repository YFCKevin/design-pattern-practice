package com.gurula.course.course.video;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "video_error_record")
public class VideoErrorRecord {
    @Id
    private String id;
    private String errorMsg;
}
