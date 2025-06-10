package com.gurula.course.course.video.command;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandWorker {
    private final CommandQueue commandQueue;

    public CommandWorker(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Scheduled(fixedRate = 5000)
    public void processCommands(){
        final List<VideoCommand> commands = commandQueue.drainCommands(100);
        for (VideoCommand cmd : commands) {
            cmd.execute();
        }
    }
}
