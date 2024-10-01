package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.Vote;
import com.example.demo.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository repository;

    public List<Vote> getAll() {
        return repository.findAll();
    }

    public List<Vote> getAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public List<Vote> getAllByPostId(Long postId) {
        return repository.findAllByPostId(postId);
    }

    public List<Vote> getAllByCommentId(Long commentId) {
        return repository.findAllByPostId(commentId);
    }

    public Vote findById(Long id) {
        Optional<Vote> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }
    public Vote add(Vote Vote) {
        return repository.save(Vote);
    }

    public Vote update(Long id, Vote newVote) {
        Optional<Vote> optional = repository.findById(id);
        return optional.map(o -> {
            o.setType(newVote.getType());
            o.setUserId(newVote.getUserId());
            o.setPostId(newVote.getPostId());
            o.setCommentId(newVote.getCommentId());
            return repository.save(o);
        }).orElse(null);
    }

    public Vote deleteById(Long id) {
        Optional<Vote> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
