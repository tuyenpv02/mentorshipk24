package com.example.demo.service.impl;

import com.example.demo.entity.Account;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Comment> getAllByPost(Post post) {
        return repository.findAllByPostId(post.getId());
    }

    public List<Comment> getAllByAccount(Account account) {
        return repository.findAllByAccount_Id(account.getId());
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
