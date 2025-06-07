package com.gurula.course.course.upload;

import com.gurula.course.course.MediaType;
import com.gurula.course.course.dto.ContentDTO;
import com.gurula.course.course.text.Text;
import com.gurula.course.course.text.TextRepository;
import com.gurula.course.exception.Result;
import org.springframework.stereotype.Component;

@Component("TEXT")
public class TextUploadStrategy implements UploadStrategy{
    private final TextRepository textRepository;

    public TextUploadStrategy(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @Override
    public Result<String, String> upload(ContentDTO dto) {
        try {
            Text text = new Text();
            text.setName(dto.getName());
            text.setDescription(dto.getDescription());
            text.setType(MediaType.TEXT);
            textRepository.save(text);
            return Result.ok("上傳成功");
        } catch (Exception e) {
            return Result.err(e.getMessage());
        }
    }
}
