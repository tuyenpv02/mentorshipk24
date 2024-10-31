package com.example.demo.service.impl;

import com.example.demo.entity.Account;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.Source;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    public List<Comment> getAll() {
        return repository.findAll();
    }

    public Page<Comment> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        return repository.findAll(pageable);
    }

    public List<Comment> getAllByPostID(Long postId) {
        return repository.findAllByPostId(postId);
    }

    public List<Comment> getAllByAccount(Long accountId) {
        return repository.findAllByAccountId(accountId);
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
//            o.setPost(Post.builder().id(newComment.getPost().getId()).build());
//            o.setAccount(Account.builder().id(newComment.getAccount().getId()).build());
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
