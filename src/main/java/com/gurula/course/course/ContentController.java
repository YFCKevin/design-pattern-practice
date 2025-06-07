package com.gurula.course.course;

import com.gurula.course.course.dto.ContentDTO;
import com.gurula.course.course.upload.UploadStrategy;
import com.gurula.course.exception.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final Map<String, UploadStrategy> strategyMap;

    public ContentController(Map<String, UploadStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadContent(@ModelAttribute ContentDTO dto){
        final UploadStrategy uploadStrategy = strategyMap.get(dto.getType().name());
        if (uploadStrategy == null) {
            return ResponseEntity.ok("Unsupported type: " + dto.getType());
        }
        final Result<String, String> result = uploadStrategy.upload(dto);
        if (result.isOk()) {
            return ResponseEntity.ok(result.unwrap());
        } else {
            return ResponseEntity.ok(result.unwrapErr());
        }

    }
}
