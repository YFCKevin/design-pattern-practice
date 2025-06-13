package com.gurula.course.discuss.observer;

import com.gurula.course.discuss.Discuss;

public interface DiscussObserver {
    void onDiscussCreated(Discuss discuss);
    void onDiscussReplied(Discuss discuss);
    void onDiscussLiked(Discuss discuss);
    void onDiscussHasPined(Discuss discuss);
    void onDiscussHasHighLighted(Discuss discuss);
    void onDiscussHasMarkAsAnswer(Discuss discuss);
    void onDiscussRemoved(Discuss discuss);
    void onDiscussDeleted(Discuss discuss);
}
