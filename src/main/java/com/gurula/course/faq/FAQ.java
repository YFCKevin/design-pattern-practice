package com.gurula.course.faq;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "faq")
public class FAQ {
    @Id
    private String id;
    private String question;
    private String answer;
    private FAQType faqType;
}
