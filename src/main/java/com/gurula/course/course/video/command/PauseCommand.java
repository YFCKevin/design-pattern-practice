package com.gurula.course.course.video.command;

import com.gurula.course.course.video.VideoEventDTO;
import com.gurula.course.exception.Result;
import org.springframework.stereotype.Component;


public class PauseCommand implements VideoCommand{
    private final VideoEventHandler handler;
    private final VideoEventDTO dto;

    public PauseCommand(VideoEventDTO dto, VideoEventHandler handler) {
        this.handler = handler;
        this.dto = dto;
    }

    @Override
    public void execute() {
        handler.saveWatchRecord(dto);
        handler.saveWatchEventRecord(dto);
    }
}
