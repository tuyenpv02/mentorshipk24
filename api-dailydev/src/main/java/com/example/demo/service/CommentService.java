package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public List<Comment> getAll() {
        return repository.findAll();
    }

    public Comment findById(Long id) {
        Optional<Comment> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }
    public Comment add(Comment Comment) {
        return repository.save(Comment);
    }

    public Comment update(Long id, Comment newComment) {
        Optional<Comment> optional = repository.findById(id);
        return optional.map(o -> {
            o.setDescription(newComment.getDescription());
            o.setPostId(newComment.getPostId());
            o.setUserId(newComment.getUserId());
            return repository.save(o);
        }).orElse(null);
    }

    public Comment deleteById(Long id) {
        Optional<Comment> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
