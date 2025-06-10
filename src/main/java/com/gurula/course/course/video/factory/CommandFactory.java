package com.gurula.course.course.video.factory;

import com.gurula.course.course.EventType;
import com.gurula.course.course.video.VideoEventDTO;
import com.gurula.course.course.video.command.*;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {
    private final VideoEventHandler handler;

    public CommandFactory(VideoEventHandler handler) {
        this.handler = handler;
    }

    public VideoCommand createCommand(VideoEventDTO dto) {
        return switch (dto.getEventType().name()) {
            case "PLAY" -> new PlayCommand(dto, handler);
            case "PAUSE" -> new PauseCommand(dto, handler);
            case "SEEK" -> new SeekCommand(dto, handler);
            case "END" -> new EndCommand(dto, handler);
            case "ERROR" -> new ErrorCommand(dto, handler);
            default -> throw new IllegalArgumentException("Unsupported event type: " + dto.getEventType());
        };
    }
}
