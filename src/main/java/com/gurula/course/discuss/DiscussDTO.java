package com.gurula.course.discuss;

import lombok.Data;

import java.util.List;
@Data
public class DiscussDTO {
    private String id;
    private String memberId;
    private String lessonId;
    private String repliedToId; // 被回覆的留言編號
    private String title;
    private String content;
    private int likeCount;
    private int dislikeCount;
    private boolean pinned;
    private boolean highlighted;
    private boolean markedAsAnswer;
    private List<Discuss> children;
    private long createAt;
    private long updateAt;
    private long deleteAt;
    private long removeAt;
}
