package com.gurula.course.course.video.command;

import com.gurula.course.course.video.VideoErrorRecord;
import com.gurula.course.course.video.VideoErrorRecordRepository;
import com.gurula.course.course.video.VideoEventDTO;
import com.gurula.course.exception.Result;
import org.springframework.stereotype.Component;


public class ErrorCommand implements VideoCommand{

    private final VideoEventHandler handler;
    private final VideoEventDTO dto;

    public ErrorCommand(VideoEventDTO dto, VideoEventHandler handler) {
        this.handler = handler;
        this.dto = dto;
    }

    @Override
    public void execute() {
        handler.saveErrorRecord(dto);
    }
}
