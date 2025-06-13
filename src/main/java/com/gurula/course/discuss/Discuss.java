package com.gurula.course.discuss;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "discuss")
public class Discuss {
    @Id
    private String id;
    private String memberId;
    private String lessonId;
    private String repliedToId; // 被回覆的留言編號
    private String title;
    private String content;
    private int likeCount = 0;
    private int dislikeCount = 0;
    private boolean pinned;
    private boolean highlighted;
    private boolean markedAsAnswer;
    private long createAt;
    private long updateAt;
    private long deleteAt;
    private long removeAt;
}
