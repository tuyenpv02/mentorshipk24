package com.example.demo.service;

import com.example.demo.entity.FollowTag;
import com.example.demo.entity.PostTag;
import com.example.demo.repository.FollowTagRepository;
import com.example.demo.repository.PostTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostTagService {

    @Autowired
    private PostTagRepository repository;

    public List<PostTag> getAll() {
        return repository.findAll();
    }

    public PostTag add(PostTag postTag) {
        return repository.save(postTag);
    }

    public PostTag deleteById(Long id) {
        Optional<PostTag> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
