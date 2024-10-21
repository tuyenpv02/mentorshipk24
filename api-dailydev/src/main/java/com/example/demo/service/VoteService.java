package com.example.demo.service;

import com.example.demo.entity.Vote;

import java.util.List;

public interface VoteService {

    List<Vote> getAll();

    List<Vote> getAllVoteByTyoe(Integer type);

    List<Vote> getAllByAccountId(Long userId);

    List<Vote> getAllByPostId(Long postId);

    List<Vote> getAllByCommentId(Long commentId);

    Vote add(Vote Vote);

    Vote deleteById(Long id);

    Boolean existsById(Long id);
}
