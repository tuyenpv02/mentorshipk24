package com.example.demo.controller;

import com.example.demo.entity.Source;
import com.example.demo.service.impl.SourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sources")
public class SourceController {

    @Autowired
    SourceServiceImpl service;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Source source) {
        return ResponseEntity.ok(service.add(source));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id
            , @RequestBody Source source) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy source"
            );
        }

        return ResponseEntity.ok(service.update(id, source));
    }
}
