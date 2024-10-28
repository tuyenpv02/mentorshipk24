package com.example.demo.service.impl;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.*;
import com.example.demo.repository.CategoryRepository;

import com.example.demo.service.CategoryService;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Category> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        return repository.findAll(pageable);
    }

    @Override
    public List<Category> getAllBySource(Long sourceId) {
        Source source = Source.builder().id(sourceId).build();
        return repository.findAllBySource(source);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }

    @Override
    public Category add(Category category) {
        return repository.save(category);
    }

    @Override
    public Category update(Long id, Category newCategory) {
        Optional<Category> optional = repository.findById(id);
        return optional.map(o -> {
            o.setName(newCategory.getName());
            o.setLink(newCategory.getLink());
            o.setSource(Source.builder().id(newCategory.getSource().getId()).build());
            return repository.save(o);
        }).orElse(null);
    }

    @Override
    public Category deleteById(Long id) {
        Optional<Category> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
