package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> getAll() {
        return repository.findAll();
    }
    public Post findById(Long id) {
        Optional<Post> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }
    public Post add(Post Post) {
        return repository.save(Post);
    }

    public Post update(Long id, Post newPost) {
        Optional<Post> optional = repository.findById(id);
        return optional.map(o -> {
            o.setTitle(newPost.getTitle());
            o.setDescription(newPost.getDescription());
            o.setTag(newPost.getTag());
            o.setUrl(newPost.getUrl());
            return repository.save(o);
        }).orElse(null);
    }

    public Post deleteById(Long id) {
        Optional<Post> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
