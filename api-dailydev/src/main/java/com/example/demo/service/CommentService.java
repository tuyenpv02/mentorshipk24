package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAll();

    Comment findById(Long id);

    Comment add(Comment comment);

    Comment update(Long id, Comment newComment);

    Comment deleteById(Long id);

    Boolean existsById(Long id);
}
