package com.gurula.course.course.video;

import com.gurula.course.course.Content;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "video")
public class Video extends Content {
    private String contentId;
    private long size;
}
