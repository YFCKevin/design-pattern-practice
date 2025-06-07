package com.gurula.course.course.upload;

import com.gurula.course.course.MediaType;
import com.gurula.course.course.dto.ContentDTO;
import com.gurula.course.course.video.Video;
import com.gurula.course.course.video.VideoRepository;
import com.gurula.course.exception.Result;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component("VIDEO")
public class VideoUploadStrategy implements UploadStrategy {
    private final VideoRepository videoRepository;

    public VideoUploadStrategy(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Result<String, String> upload(ContentDTO dto) {
        try {
            final MultipartFile file = dto.getFile();
            Video video = new Video();
            video.setName(dto.getName());
            video.setType(MediaType.VIDEO);
            video.setDescription(dto.getDescription());
            video.setContentId(UUID.randomUUID().toString());
            video.setSize(file.getSize());
            final Video savedVideo = videoRepository.save(video);

            final String filename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(filename);
            String filePath = savedVideo.getContentId() + "." + extension;

            this.saveFile(file, filePath);
            return Result.ok("上傳成功");
        } catch (Exception e) {
            return Result.err(e.getMessage());
        }
    }

    private void saveFile(MultipartFile file, String filePath) {
        System.out.println("儲存影片檔");
    }
}
