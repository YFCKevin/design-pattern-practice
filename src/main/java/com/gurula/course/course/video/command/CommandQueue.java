package com.gurula.course.course.video.command;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class CommandQueue {
    private final Queue<VideoCommand> queue = new ConcurrentLinkedQueue<>();

    public void enqueue(VideoCommand command) {
        queue.offer(command);
    }

    public List<VideoCommand> drainCommands(int maxSize){
        List<VideoCommand> batch = new ArrayList<>();
        for (int i = 0; i < maxSize && !queue.isEmpty(); i++) {
            final VideoCommand cmd = queue.poll();
            if (cmd != null) {
                batch.add(cmd);
            }
        }
        return batch;
    }
}
