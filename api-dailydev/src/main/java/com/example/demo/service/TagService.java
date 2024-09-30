package com.example.demo.service;

import com.example.demo.entity.Tag;
import com.example.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository repository;

    public List<Tag> getAll() {
        return repository.findAll();
    }
    public Tag findById(Long id) {
        Optional<Tag> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }
    public Tag add(Tag tag) {
        return repository.save(tag);
    }

    public Tag update(Long id, Tag newTag) {
        Optional<Tag> optional = repository.findById(id);
        return optional.map(o -> {
            o.setName(newTag.getName());
            return repository.save(o);
        }).orElse(null);
    }

    public Tag deleteById(Long id) {
        Optional<Tag> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
