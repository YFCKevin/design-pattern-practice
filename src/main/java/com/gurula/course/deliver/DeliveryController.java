package com.gurula.course.deliver;

import com.gurula.course.deliver.dto.ShippingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliver")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/ship")
    public ResponseEntity<?> shippingArrangement(@RequestBody ShippingDTO dto) {
        deliveryService.ship(dto);
        return ResponseEntity.ok("排定出貨");
    }

    @GetMapping("/{id}/complete")
    public ResponseEntity<?> shippingComplete(@PathVariable String id) {
        deliveryService.complete(id);
        return ResponseEntity.ok("已送達並取貨");
    }
}
