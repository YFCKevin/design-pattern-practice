package com.gurula.course.course.questionnaire.CoR;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public abstract class ResponseHandler {
    protected ResponseHandler next;
    public ResponseHandler(ResponseHandler next){
        this.next = next;
    }
    public String response(String answer){
        final QuestAnswerType type = aiClassifier(answer);
        if (match(type)) {
            return doHandler(answer, type);
        } else if (next != null) {
            return next.doHandler(answer, type);
        } else {
            return "我們收到您的回覆，稍後將有專人與您聯繫。";
        }
    }
    protected abstract boolean match(QuestAnswerType type);
    protected abstract String doHandler(String answer, QuestAnswerType type);

    private QuestAnswerType aiClassifier(String answer) {
        // AI判斷answer是屬於哪個類型
        List<QuestAnswerType> categories = Arrays.asList(QuestAnswerType.EMOTIONAL, QuestAnswerType.TECHNICAL, QuestAnswerType.CAREER);
        Random random = new Random();
        return categories.get(random.nextInt(categories.size()));
    }
}
