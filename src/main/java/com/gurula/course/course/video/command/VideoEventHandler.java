package com.gurula.course.course.video.command;

import com.gurula.course.course.Course;
import com.gurula.course.course.CourseRepository;
import com.gurula.course.course.Lesson;
import com.gurula.course.course.LessonRepository;
import com.gurula.course.course.video.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VideoEventHandler {
    private final WatchRecordRepository watchRecordRepository;
    private final WatchEventRecordRepository watchEventRecordRepository;
    private final VideoErrorRecordRepository videoErrorRecordRepository;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public VideoEventHandler(WatchRecordRepository watchRecordRepository, WatchEventRecordRepository watchEventRecordRepository, VideoErrorRecordRepository videoErrorRecordRepository, LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.watchRecordRepository = watchRecordRepository;
        this.watchEventRecordRepository = watchEventRecordRepository;
        this.videoErrorRecordRepository = videoErrorRecordRepository;
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    protected void saveWatchRecord(VideoEventDTO dto) {
        WatchRecord watchRecord = new WatchRecord();
        watchRecord.setCourseId(dto.getCourseId());
        watchRecord.setLessonId(dto.getLessonId());
        watchRecord.setMemberId(dto.getMemberId());
        watchRecord.setContentId(dto.getContentId());
        watchRecord.setWatchTime(dto.getCurrentTime());
        watchRecordRepository.save(watchRecord);
    }

    @Transactional
    protected void saveWatchEventRecord(VideoEventDTO dto) {
        final Lesson lesson = lessonRepository.findById(dto.getLessonId()).get();
        final Course course = courseRepository.findById(dto.getCourseId()).get();
        WatchEventRecord watchEventRecord = new WatchEventRecord();
        watchEventRecord.setCourseId(dto.getCourseId());
        watchEventRecord.setEventType(dto.getEventType());
        watchEventRecord.setMemberId(dto.getMemberId());
        watchEventRecord.setLessonId(dto.getLessonId());
        watchEventRecord.setLessonName(lesson.getName());
        watchEventRecord.setCourseName(course.getName());
        watchEventRecord.setStartWatchTime(dto.getStartWatchTime());
        watchEventRecord.setEndWatchTime(dto.getEndWatchTime());
        watchEventRecord.setCreateAt(System.currentTimeMillis());
        watchEventRecord.setCurrentWatchTime(dto.getCurrentTime());
        watchEventRecord.setTotalWatchTime(dto.getEndWatchTime() - dto.getStartWatchTime());
        watchEventRecord.setContentId(dto.getContentId());
        watchEventRecordRepository.save(watchEventRecord);
    }

    @Transactional
    protected void saveErrorRecord(VideoEventDTO dto) {
        VideoErrorRecord videoErrorRecord = new VideoErrorRecord();
        videoErrorRecord.setErrorMsg(dto.getErrorMsg());
        videoErrorRecord.setCourseId(dto.getCourseId());
        videoErrorRecord.setMemberId(dto.getMemberId());
        videoErrorRecord.setLessonId(dto.getLessonId());
        videoErrorRecord.setContentId(dto.getContentId());
        videoErrorRecordRepository.save(videoErrorRecord);
    }
}
