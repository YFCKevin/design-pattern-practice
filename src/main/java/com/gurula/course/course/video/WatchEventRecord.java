package com.gurula.course.course.video;

import com.gurula.course.course.EventType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "watch_event_record")
public class WatchEventRecord {
    @Id
    private String id;
    private String memberId;
    private String courseId;
    private String lessonId;
    private String courseName;
    private String lessonName;
    private EventType eventType;
    private Long startWatchTime;
    private Long endWatchTime;
    private Long createAt;
    private Double currentWatchTime;
    private Long totalWatchTime;
}
