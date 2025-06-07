package com.gurula.course.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LessonRepository extends MongoRepository<Lesson, String> {
    List<Lesson> findAllByCourseIdIn(List<String> courseIds);
    List<Lesson> findByIdIn(List<String> lessonIds);
}
