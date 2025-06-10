package com.gurula.course.course.video;

import com.gurula.course.course.video.command.*;
import com.gurula.course.course.video.factory.CommandFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/video")
public class VideoController {
    private final CommandQueue commandQueue;
    private final CommandFactory commandFactory;

    public VideoController(CommandQueue commandQueue, CommandFactory commandFactory) {
        this.commandQueue = commandQueue;
        this.commandFactory = commandFactory;
    }

    @PostMapping("/events")
    public ResponseEntity<?> videoEvents (@RequestBody VideoEventDTO dto){
        VideoCommand videoCommand = commandFactory.createCommand(dto);
        commandQueue.enqueue(videoCommand);
        return ResponseEntity.ok("ok");
    }
}
