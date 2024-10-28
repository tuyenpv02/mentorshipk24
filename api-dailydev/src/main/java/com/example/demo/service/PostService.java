package com.example.demo.service;

import com.example.demo.entity.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Page<Post> getAll(int page, int size);
    List<Post> getAll();

    List<Post> getAllByCategoryId(Long cateId);

    List<Post> getAllByUserId(Long userId);


    Post findById(Long id);

    Post findByLink(String link);

    Optional<Post> findByGuId(String guId);

    Post findByName(String name);


    Post save(Post post);

    Post update(Long id, Post newPost);

    Post deleteById(Long id);

    Boolean existsById(Long id);
}
