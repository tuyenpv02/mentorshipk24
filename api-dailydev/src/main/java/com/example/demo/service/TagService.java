package com.example.demo.service;

import com.example.demo.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAll();

    Tag findById(Long id);

    Tag add(Tag tag);

    Tag update(Long id, Tag newTag);

    Tag deleteById(Long id);

    Boolean existsById(Long id);
}
