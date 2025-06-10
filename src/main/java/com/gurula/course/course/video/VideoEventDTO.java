package com.gurula.course.course.video;

import com.gurula.course.course.EventType;
import lombok.Data;

@Data
public class VideoEventDTO {
    private EventType eventType;
    private String memberId;
    private String courseId;
    private String lessonId;
    private long startWatchTime;
    private long endWatchTime;
    private long createAt;
    private double currentTime;
    private String errorMsg;
}
