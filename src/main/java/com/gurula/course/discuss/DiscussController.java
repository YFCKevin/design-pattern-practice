package com.gurula.course.discuss;

import com.gurula.course.exception.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discuss")
public class DiscussController {
    private final DiscussService discussService;
    public DiscussController(DiscussService discussService) {
        this.discussService = discussService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody DiscussDTO dto){
        if (StringUtils.isNotBlank(dto.getRepliedToId())) {
            return ResponseEntity.ok(Result.err("操作無效，此為回覆留言"));
        } else {
            Discuss discuss = new Discuss();
            discuss.setTitle(dto.getTitle());
            discuss.setContent(dto.getContent());
            discuss.setCreateAt(System.currentTimeMillis());
            discuss.setMemberId(dto.getMemberId());
            discuss.setLessonId(dto.getLessonId());
            Result<String, String> result = discussService.save(discuss);
            if (result.isErr()) {
                return ResponseEntity.ok(result.unwrapErr());
            } else {
                return ResponseEntity.ok(result.unwrap());
            }
        }
    }


    @PostMapping("/reply")
    public ResponseEntity<?> reply(@RequestBody DiscussDTO dto){
        if (StringUtils.isBlank(dto.getRepliedToId())) {
            return ResponseEntity.ok(Result.err("操作無效，此為新增留言"));
        } else {
            Discuss discuss = new Discuss();
            discuss.setTitle(dto.getTitle());
            discuss.setContent(dto.getContent());
            discuss.setCreateAt(System.currentTimeMillis());
            discuss.setMemberId(dto.getMemberId());
            discuss.setLessonId(dto.getLessonId());
            discuss.setRepliedToId(discuss.getRepliedToId());
            Result<String, String> result = discussService.reply(discuss);
            if (result.isErr()) {
                return ResponseEntity.ok(result.unwrapErr());
            } else {
                return ResponseEntity.ok(result.unwrap());
            }
        }
    }


    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody DiscussDTO dto){
        Discuss discuss = new Discuss();
        discuss.setTitle(dto.getTitle());
        discuss.setContent(dto.getContent());
        discuss.setUpdateAt(System.currentTimeMillis());
        Result<String, String> result = discussService.edit(discuss);
        if (result.isErr()) {
            return ResponseEntity.ok(result.unwrapErr());
        } else {
            return ResponseEntity.ok(result.unwrap());
        }
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable String id){
        Result<String, String> result = discussService.delete(id);
        if (result.isErr()) {
            return ResponseEntity.ok(result.unwrapErr());
        } else {
            return ResponseEntity.ok(result.unwrap());
        }
    }


    @PatchMapping("/{id}/like")
    public ResponseEntity<?> like(@PathVariable String id){
        Result<String, String> result = discussService.like(id);
        if (result.isErr()) {
            return ResponseEntity.ok(result.unwrapErr());
        } else {
            return ResponseEntity.ok(result.unwrap());
        }
    }


    @PatchMapping("/{id}/unlike")
    public ResponseEntity<?> unlike(@PathVariable String id){
        Result<String, String> result = discussService.unlike(id);
        if (result.isErr()) {
            return ResponseEntity.ok(result.unwrapErr());
        } else {
            return ResponseEntity.ok(result.unwrap());
        }
    }


    @PatchMapping("/{id}/dislike")
    public ResponseEntity<?> dislike(@PathVariable String id){
        Result<String, String> result = discussService.dislike(id);
        if (result.isErr()) {
            return ResponseEntity.ok(result.unwrapErr());
        } else {
            return ResponseEntity.ok(result.unwrap());
        }
    }


    @PatchMapping("/{id}/pin")
    public ResponseEntity<?> pin(@PathVariable String id){
        Result<String, String> result = discussService.pin(id);
        if (result.isErr()) {
            return ResponseEntity.ok(result.unwrapErr());
        } else {
            return ResponseEntity.ok(result.unwrap());
        }
    }


    @PatchMapping("/{id}/unpin")
    public ResponseEntity<?> unpin(@PathVariable String id){
        Result<String, String> result = discussService.unpin(id);
        if (result.isErr()) {
            return ResponseEntity.ok(result.unwrapErr());
        } else {
            return ResponseEntity.ok(result.unwrap());
        }
    }


    @PatchMapping("/{id}/highlight")
    public ResponseEntity<?> highlight(@PathVariable String id){
        Result<String, String> result = discussService.highlight(id);
        if (result.isErr()) {
            return ResponseEntity.ok(result.unwrapErr());
        } else {
            return ResponseEntity.ok(result.unwrap());
        }
    }


    @PatchMapping("/{id}/markAsAnswer")
    public ResponseEntity<?> markAsAnswer(@PathVariable String id){
        Result<String, String> result = discussService.markAsAnswer(id);
        if (result.isErr()) {
            return ResponseEntity.ok(result.unwrapErr());
        } else {
            return ResponseEntity.ok(result.unwrap());
        }
    }


    @PatchMapping("/{id}/remove")
    public ResponseEntity<?> remove(@PathVariable String id){
        Result<String, String> result = discussService.remove(id);
        if (result.isErr()) {
            return ResponseEntity.ok(result.unwrapErr());
        } else {
            return ResponseEntity.ok(result.unwrap());
        }
    }
}
