package com.gurula.course.course.video;

import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService{
    private final VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }
}
