package com.gurula.course.course.video.command;

import com.gurula.course.course.video.VideoEventDTO;
import com.gurula.course.course.video.WatchRecordRepository;
import com.gurula.course.exception.Result;
import org.springframework.stereotype.Component;


public class EndCommand implements VideoCommand{
    private final VideoEventHandler handler;
    private final VideoEventDTO dto;

    public EndCommand(VideoEventDTO dto, VideoEventHandler handler) {
        this.handler = handler;
        this.dto = dto;
    }

    @Override
    public void execute() {
        handler.saveWatchRecord(dto);
        handler.saveWatchEventRecord(dto);
    }
}
