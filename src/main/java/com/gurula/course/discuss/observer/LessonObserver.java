package com.gurula.course.discuss.observer;

import com.gurula.course.course.LessonService;
import com.gurula.course.discuss.Discuss;
import org.springframework.stereotype.Component;

@Component
public class LessonObserver implements DiscussObserver{
    private final LessonService lessonService;

    public LessonObserver(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Override
    public void onDiscussCreated(Discuss discuss) {
        lessonService.updateDiscussInfo(discuss);
    }

    @Override
    public void onDiscussReplied(Discuss discuss) {

    }

    @Override
    public void onDiscussLiked(Discuss discuss) {

    }

    @Override
    public void onDiscussHasPined(Discuss discuss) {

    }

    @Override
    public void onDiscussHasHighLighted(Discuss discuss) {

    }

    @Override
    public void onDiscussHasMarkAsAnswer(Discuss discuss) {

    }

    @Override
    public void onDiscussRemoved(Discuss discuss) {
        lessonService.updateDiscussInfo(discuss);
    }

    @Override
    public void onDiscussDeleted(Discuss discuss) {
        lessonService.updateDiscussInfo(discuss);
    }
}
