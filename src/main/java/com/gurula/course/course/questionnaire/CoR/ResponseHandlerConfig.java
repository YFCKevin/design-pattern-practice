package com.gurula.course.course.questionnaire.CoR;

import com.gurula.course.faq.FAQRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseHandlerConfig {

    @Bean
    public ResponseHandler responseHandler(FAQRepository faqRepository) {
        return new EmotionalHandler(
                new TechnicalHandler(
                        new CareerHandler(null, faqRepository),
                        faqRepository),
                faqRepository);
    }
}
