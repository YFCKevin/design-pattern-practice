package com.gurula.course.course.questionnaire.CoR;

import com.gurula.course.faq.FAQ;
import com.gurula.course.faq.FAQRepository;

public class CareerHandler extends ResponseHandler{
    private final FAQRepository faqRepository;
    public CareerHandler(ResponseHandler next, FAQRepository faqRepository) {
        super(next);
        this.faqRepository = faqRepository;
    }

    @Override
    protected boolean match(QuestAnswerType type) {
        return QuestAnswerType.CAREER.equals(type);
    }

    @Override
    protected String doHandler(String answer, QuestAnswerType type) {
        // 讓AI根據answer做回應
        final FAQ faq = faqRepository.findByFaqType(type.name()).get();
        return null;
    }

}
