package com.gurula.course.course.video.command;

import com.gurula.course.course.video.VideoEventDTO;


public class SeekCommand implements VideoCommand{
    private final VideoEventHandler handler;
    private final VideoEventDTO dto;

    public SeekCommand(VideoEventDTO dto, VideoEventHandler handler) {
        this.handler = handler;
        this.dto = dto;
    }

    @Override
    public void execute() {
        handler.saveWatchRecord(dto);
        handler.saveWatchEventRecord(dto);
    }
}
