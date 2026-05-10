package com.rymar.controller;

import com.rymar.kfk.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController("/api")
@RequiredArgsConstructor
public class ProductController{
    private final KafkaSender kafkaSender ;

    @GetMapping("/get")
    ResponseEntity<String> get(){
        var map = new HashMap<>();
        map.put("name","Sasha");
        map.put("age",11);
        kafkaSender.sendMessage("orymar-events", map);
        return ResponseEntity.ok("Got it");
    }

}
