package com.example.demo.service;

import com.example.demo.dto.response.PageResponse;
import com.example.demo.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Page<Post> getAll(int page, int size);

    List<Post> getAll();

    PageResponse<Post> getAll(int page, int size, String keyword);

    PageResponse<Post> getAllByCategoryId(Long cateId, int page, int size);

    PageResponse<Post> getAllByUserId(Long userId, int page, int size);

//
    Post findById(Long id);

    Post findByLink(String link);

    Optional<Post> findByGuId(String guId);

    Post findByName(String name);


    Post save(Post post);

    Post update(Long id, Post newPost);

    Boolean deleteById(Long id);

    Boolean existsById(Long id);
}
