package com.gurula.course.course;

import com.gurula.course.course.dto.CourseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save (@RequestBody CourseDTO dto){
        logger.info("[save course]");
        courseService.save(dto);
        return ResponseEntity.ok("ok");
    }


    @GetMapping("/all")
    public ResponseEntity<?> all (){
        logger.info("[all course]");
        List<CourseDTO> courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }
}
