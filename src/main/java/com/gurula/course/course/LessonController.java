package com.gurula.course.course;

import com.gurula.course.course.dto.LessonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    private final Logger logger = LoggerFactory.getLogger(LessonController.class);
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save (@RequestBody List<LessonDTO> lessons){
        logger.info("[save lesson]");
        lessonService.saveAll(lessons);
        return ResponseEntity.ok("ok");
    }
}
