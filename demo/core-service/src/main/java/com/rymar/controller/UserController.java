package com.rymar.controller;

import com.rymar.common.dto.CreateProductDto;
import com.rymar.common.events.CreateProductEvent;
import com.rymar.kfk.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final KafkaSender kafkaSender ;

    @PostMapping()
    ResponseEntity post(CreateProductDto productDto){
        CreateProductEvent obj = new CreateProductEvent();
        obj.createProductDto = productDto;
        kafkaSender.sendMessage(obj.topic, obj);
        return ResponseEntity.ok(obj);
    }




}
