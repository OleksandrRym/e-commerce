package com.rymar.controller;

import com.rymar.common.dto.CreateProductDto;
import com.rymar.common.dto.UpdateProductDto;
import com.rymar.common.events.CreateProductEvent;
import com.rymar.common.events.UpdateProductEvent;
import com.rymar.kfk.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class UserController {
    private final KafkaSender kafkaSender ;

    @PostMapping()
    ResponseEntity post(@RequestBody CreateProductDto productDto){
        CreateProductEvent obj = new CreateProductEvent();
        obj.createProductDto = productDto;
        kafkaSender.sendMessage(obj.topic, obj);
        return ResponseEntity.ok(obj);
    }

    @PatchMapping()
    ResponseEntity update(@RequestBody UpdateProductDto productDto){
        UpdateProductEvent obj = new UpdateProductEvent();
        obj.updateProductDto = productDto;
        kafkaSender.sendMessage(obj.topic, obj);
        return ResponseEntity.ok(obj);
    }
}
