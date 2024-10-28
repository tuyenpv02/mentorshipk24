package com.example.demo.service;

import com.example.demo.dto.response.PageResponse;
import com.example.demo.entity.Bookmark;

import java.util.List;

public interface BookmarkService {

    PageResponse<Bookmark> getAllByUserId(Long userId, int pageNumber, int size);

    Bookmark findById(Long id);

    Bookmark add(Bookmark Bookmark);

    Bookmark update(Long id, Bookmark newBookmark);

    Bookmark deleteById(Long id);

    Boolean existsById(Long id);
}
