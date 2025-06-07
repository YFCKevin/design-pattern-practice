package com.gurula.course.course.upload;

import com.gurula.course.course.dto.ContentDTO;
import com.gurula.course.exception.Result;

public interface UploadStrategy {
    Result<String, String> upload(ContentDTO dto);
}
