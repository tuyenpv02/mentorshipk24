package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Category;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    List<Comment> getAll();

    Page<Comment> getAll(int page, int size);

    List<Comment> getAllByPostID(Long postId);

    List<Comment> getAllByAccount(Long accountId);

    Comment findById(Long id);

    Comment add(Comment comment);

    Comment update(Long id, Comment newComment);

    Comment deleteById(Long id);

    Boolean existsById(Long id);
}
