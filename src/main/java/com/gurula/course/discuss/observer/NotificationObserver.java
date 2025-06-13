package com.gurula.course.discuss.observer;

import com.gurula.course.discuss.Discuss;
import org.springframework.stereotype.Component;

@Component
public class NotificationObserver implements DiscussObserver{
    @Override
    public void onDiscussCreated(Discuss discuss) {
        System.out.println("通知講師, 上課and訂閱的學生");
    }

    @Override
    public void onDiscussReplied(Discuss discuss) {
        System.out.println("通知回覆留言的人");
    }

    @Override
    public void onDiscussLiked(Discuss discuss) {
        System.out.println("通知點讚的留言人");
    }

    @Override
    public void onDiscussHasPined(Discuss discuss) {
        System.out.println("通知被置頂的留言人");
    }

    @Override
    public void onDiscussHasHighLighted(Discuss discuss) {
        System.out.println("通知被設為精選的留言人");
    }

    @Override
    public void onDiscussHasMarkAsAnswer(Discuss discuss) {
        System.out.println("通知被設為解決方案的留言人");
    }

    @Override
    public void onDiscussRemoved(Discuss discuss) {
        System.out.println("通知被檢舉而下架的留言人");
    }

    @Override
    public void onDiscussDeleted(Discuss discuss) {

    }
}
