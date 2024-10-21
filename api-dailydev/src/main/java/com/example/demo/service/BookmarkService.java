package com.example.demo.service;

import com.example.demo.entity.Bookmark;

import java.util.List;

public interface BookmarkService {

    List<Bookmark> getAllByUserId(Long userId);

    Bookmark findById(Long id);

    Bookmark add(Bookmark Bookmark);

    Bookmark update(Long id, Bookmark newBookmark);

    Bookmark deleteById(Long id);

    Boolean existsById(Long id);
}
