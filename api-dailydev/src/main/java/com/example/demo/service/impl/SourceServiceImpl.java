package com.example.demo.service.impl;

import com.example.demo.entity.Account;
import com.example.demo.entity.Post;
import com.example.demo.entity.Source;
import com.example.demo.entity.Tag;
import com.example.demo.repository.SourceRepository;
import com.example.demo.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceRepository repository;

    public List<Source> getAll() {
        return repository.findAll();
    }

    public Page<Source> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        return repository.findAll(pageable);
    }


    public Source add(Source source) {
        return repository.save(source);
    }

    public Source update(Long id, Source newSource) {
        Optional<Source> optional = repository.findById(id);
        return optional.map(o -> {
            o.setTitle(newSource.getTitle());
            o.setUrl(newSource.getUrl());
            return repository.save(o);
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
