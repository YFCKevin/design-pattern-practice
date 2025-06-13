package com.gurula.course.discuss;

import com.gurula.course.discuss.observer.DiscussObserver;
import com.gurula.course.exception.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussServiceImpl implements DiscussService{
    private final DiscussRepository discussRepository;
    private final List<DiscussObserver> observers;

    public DiscussServiceImpl(DiscussRepository discussRepository, List<DiscussObserver> observers) {
        this.discussRepository = discussRepository;
        this.observers = observers;
    }

    @Override
    public Result<String, String> save(Discuss discuss) {
        final Discuss savedDiscuss = discussRepository.save(discuss);

        for (DiscussObserver observer : observers) {
            observer.onDiscussCreated(savedDiscuss);
        }

        return Result.ok("留言完成");
    }

    @Override
    public Result<String, String> remove(String id) {
        final Discuss discuss = discussRepository.findById(id).get();
        discuss.setRemoveAt(System.currentTimeMillis());
        final Discuss savedDiscuss = discussRepository.save(discuss);
        for (DiscussObserver observer : observers) {
            observer.onDiscussRemoved(savedDiscuss);
        }
        return Result.ok("刪除留言完成");
    }

    @Override
    public Result<String, String> markAsAnswer(String id) {
        final Discuss discuss = discussRepository.findById(id).get();
        discuss.setMarkedAsAnswer(true);
        final Discuss savedDiscuss = discussRepository.save(discuss);
        for (DiscussObserver observer : observers) {
            observer.onDiscussHasMarkAsAnswer(savedDiscuss);
        }
        return Result.ok("標記留言完成");
    }

    @Override
    public Result<String, String> highlight(String id) {
        final Discuss discuss = discussRepository.findById(id).get();
        discuss.setHighlighted(true);
        final Discuss savedDiscuss = discussRepository.save(discuss);
        for (DiscussObserver observer : observers) {
            observer.onDiscussHasHighLighted(savedDiscuss);
        }
        return Result.ok("標記留言完成");
    }

    @Override
    public Result<String, String> unpin(String id) {
        final Discuss discuss = discussRepository.findById(id).get();
        discuss.setPinned(false);
        discussRepository.save(discuss);
        return Result.ok("去置頂留言完成");
    }

    @Override
    public Result<String, String> pin(String id) {
        final Discuss discuss = discussRepository.findById(id).get();
        discuss.setPinned(true);
        final Discuss savedDiscuss = discussRepository.save(discuss);
        for (DiscussObserver observer : observers) {
            observer.onDiscussHasPined(savedDiscuss);
        }
        return Result.ok("置頂留言完成");
    }

    @Override
    public Result<String, String> dislike(String id) {
        final Discuss discuss = discussRepository.findById(id).get();
        discuss.setDislikeCount(discuss.getDislikeCount() + 1);
        discussRepository.save(discuss);
        return Result.ok("點爛留言完成");
    }

    @Override
    public Result<String, String> unlike(String id) {
        final Discuss discuss = discussRepository.findById(id).get();
        discuss.setLikeCount(discuss.getLikeCount() - 1);
        final Discuss savedDiscuss = discussRepository.save(discuss);
        for (DiscussObserver observer : observers) {
            observer.onDiscussLiked(savedDiscuss);
        }
        return Result.ok("去掉讚留言完成");
    }

    @Override
    public Result<String, String> like(String id) {
        final Discuss discuss = discussRepository.findById(id).get();
        discuss.setLikeCount(discuss.getLikeCount() + 1);
        final Discuss savedDiscuss = discussRepository.save(discuss);
        for (DiscussObserver observer : observers) {
            observer.onDiscussCreated(savedDiscuss);
        }
        return Result.ok("按讚留言完成");
    }

    @Override
    public Result<String, String> delete(String id) {
        final Discuss discuss = discussRepository.findById(id).get();
        discuss.setDeleteAt(System.currentTimeMillis());
        final Discuss savedDiscuss = discussRepository.save(discuss);
        for (DiscussObserver observer : observers) {
            observer.onDiscussDeleted(savedDiscuss);
        }
        return Result.ok("刪除留言完成");
    }

    @Override
    public Result<String, String> edit(Discuss discuss) {
        discussRepository.save(discuss);
        return Result.ok("變更留言完成");
    }

    @Override
    public Result<String, String> reply(Discuss discuss) {
        final Discuss savedDiscuss = discussRepository.save(discuss);
        for (DiscussObserver observer : observers) {
            observer.onDiscussReplied(savedDiscuss);
        }
        return Result.ok("回覆留言完成");
    }
}
