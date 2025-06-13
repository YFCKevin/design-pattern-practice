package com.gurula.course.course;

import com.gurula.course.course.dto.LessonDTO;
import com.gurula.course.discuss.Discuss;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService{
    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void saveAll(List<LessonDTO> lessons) {
        final List<Lesson> lessonList = lessons.stream().map(lessonDTO -> {
            Lesson lesson = new Lesson();
            lesson.setName(lessonDTO.getName());
            lesson.setContentId(lessonDTO.getContent().getId());
            lesson.setCourseId(lessonDTO.getCourseId());
            lesson.setParentId(lessonDTO.getParentId());
            return lesson;
        }).toList();
        lessonRepository.saveAll(lessonList);
    }

    @Override
    public void updateDiscussInfo(Discuss discuss) {
        final Lesson lesson = lessonRepository.findById(discuss.getLessonId()).get();
        if (discuss.getDeleteAt() > 0) {
            lesson.setDiscussCount(lesson.getDiscussCount() - 1);
        } else if (discuss.getRemoveAt() > 0) {
            lesson.setDiscussCount(lesson.getDiscussCount() - 1);
        } else {
            lesson.setLatestDiscussTime(discuss.getCreateAt());
            lesson.setDiscussCount(lesson.getDiscussCount() + 1);
        }
        lessonRepository.save(lesson);
    }
}
