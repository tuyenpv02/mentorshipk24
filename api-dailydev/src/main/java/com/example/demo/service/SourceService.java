package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.Source;
import com.example.demo.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceService {

    @Autowired
    private SourceRepository repository;

    public Source add(Source source) {
        return repository.save(source);
    }

    public List<Source> getAll() {
        return repository.findAll();
    }

}
