package com.example.demo.repository;

import com.example.demo.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByAccountId(Long userId);

    List<Vote> findAllByPostId(Long postId);

    List<Vote> findAllByPostIdAndType(Long postId, Integer type);

    List<Vote> findAllByCommentId(Long commentId);


}
