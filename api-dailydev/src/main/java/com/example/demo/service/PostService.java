package com.example.demo.service;

import com.example.demo.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAll();

    List<Post> getAllByCategoryId(Long cateId);

    List<Post> getAllByUserId(Long userId);


    Post findById(Long id);

    Post findByLink(String link);

    Post findByGuId(String guId);

    Post findByName(String name);


    Post add(Post post);

    Post update(Long id, Post newPost);

    Post deleteById(Long id);

    Boolean existsById(Long id);
}
