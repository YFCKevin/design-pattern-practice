package com.gurula.course.course;

import com.gurula.course.course.dto.ContentDTO;
import com.gurula.course.course.dto.CourseDTO;
import com.gurula.course.course.dto.LessonDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.gurula.course.course.Course.constructCourse;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final ContentRepository contentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, LessonRepository lessonRepository,
                             ContentRepository contentRepository) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
        this.contentRepository = contentRepository;
    }

    @Override
    public void save(CourseDTO dto) {
        courseRepository.save(constructCourse(dto));
    }

    @Override
    public List<CourseDTO> findAll() {
        // 取得所有課程
        final List<Course> courses = courseRepository.findAll();
        final List<String> courseIds = courses.stream()
                .map(Course::getId).toList();
        // 取得所有主單元
        List<Lesson> lessons = lessonRepository.findAllByCourseIdIn(courseIds);

        List<String> childrenLessonIds = lessons.stream()
                .map(Lesson::getLessonIds)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .toList();
        // 取得所有子單元
        final List<Lesson> childrenLessons = lessonRepository.findByIdIn(childrenLessonIds);

        // 取得所有的ContentDTO與lessonId對應
        final Map<String, String> lessonContentMap = lessons.stream()
                .collect(Collectors.toMap(Lesson::getId, Lesson::getContentId));
        final Map<String, String> childrenLessonContentMap = childrenLessons.stream()
                .collect(Collectors.toMap(Lesson::getId, Lesson::getContentId));
        lessonContentMap.putAll(childrenLessonContentMap);
        // Key: contentId, value: lessonId
        Map<String, String> contentToLessonMap = lessonContentMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        List<Content> contents = contentRepository.findAllById(lessonContentMap.values());
        Map<String, ContentDTO> lessonToContentDTOMap = contents.stream()
                .map(content -> {
                    ContentDTO dto = new ContentDTO();
                    dto.setId(content.getId());
                    dto.setName(content.getName());
                    dto.setDescription(content.getDescription());
                    dto.setType(content.getType());
                    return dto;
                })
                .collect(Collectors.toMap(
                        content -> contentToLessonMap.get(content.getId()),
                        Function.identity()
                ));


        final Map<String, List<LessonDTO>> childrenLessonDTOMap = childrenLessons.stream()
                .map(lesson -> {
                    LessonDTO dto = new LessonDTO();
                    dto.setId(lesson.getId());
                    dto.setName(lesson.getName());
                    dto.setCourseId(lesson.getCourseId());
                    dto.setParentId(lesson.getParentId());
                    dto.setContent(lessonToContentDTOMap.get(lesson.getId()));
                    return dto;
                })
                .collect(Collectors.groupingBy(LessonDTO::getParentId));

        final Map<String, List<LessonDTO>> mainLessonDTOMap = lessons.stream().map(lesson -> {
            LessonDTO dto = new LessonDTO();
            dto.setId(lesson.getId());
            dto.setName(lesson.getName());
            dto.setCourseId(lesson.getCourseId());
            dto.setParentId(lesson.getParentId());
            dto.setContent(lessonToContentDTOMap.get(lesson.getId()));
            dto.setLessons(childrenLessonDTOMap.get(lesson.getId()));
            return dto;
        }).collect(Collectors.groupingBy(LessonDTO::getCourseId));

        return courses.stream()
                .map(course -> {
                    CourseDTO dto = new CourseDTO();
                    dto.setId(course.getId());
                    dto.setName(course.getName());
                    dto.setPrice(course.getPrice());
                    dto.setLessons(mainLessonDTOMap.get(course.getId()));
                    return dto;
                }).toList();
    }
}
