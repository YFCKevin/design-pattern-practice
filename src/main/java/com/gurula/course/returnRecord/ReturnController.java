package com.gurula.course.returnRecord;

import com.gurula.course.order.dto.ReturnRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
public class ReturnController {
    private final ReturnService returnService;

    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyReturn (@RequestBody ReturnRequestDTO dto){
        returnService.applyReturn(dto);
        return ResponseEntity.ok("客戶申請退貨");
    }


    @GetMapping("/{id}/approve")
    public ResponseEntity<?> approveReturn (@PathVariable String id){
        returnService.approveReturn(id);
        return ResponseEntity.ok("同意退貨");
    }

    @PostMapping("/reason/edit")
    public ResponseEntity<?> editReason (@RequestBody ReturnRequestDTO dto){
        returnService.editReason(dto);
        return ResponseEntity.ok("退貨原因變更完成");
    }

    @GetMapping("/{id}/reject")
    public ResponseEntity<?> rejectReturn (@PathVariable String id){
        returnService.rejectReturn(id);
        return ResponseEntity.ok("拒絕退貨");
    }
}
