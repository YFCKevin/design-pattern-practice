package com.gurula.course.discuss;

import com.gurula.course.exception.Result;

public interface DiscussService {
    Result<String, String> save(Discuss discuss);

    Result<String, String> remove(String id);

    Result<String, String> markAsAnswer(String id);

    Result<String, String> highlight(String id);

    Result<String, String> unpin(String id);

    Result<String, String> pin(String id);

    Result<String, String> dislike(String id);

    Result<String, String> unlike(String id);

    Result<String, String> like(String id);

    Result<String, String> delete(String id);

    Result<String, String> edit(Discuss discuss);

    Result<String, String> reply(Discuss discuss);
}
