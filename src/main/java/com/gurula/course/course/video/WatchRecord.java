package com.gurula.course.course.video;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 紀錄當次觀看時長
 */
@Data
@Document(collection = "watch_record")
public class WatchRecord {
    @Id
    private String id;
    private String memberId;
    private String courseId;
    private String lessonId;
    private String contentId;
    private Double watchTime;   // 當次觀看時長
}
