package com.example.demo.repository;

import com.example.demo.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByUserId(Long userId);
    List<Follow> findAllByFollowId(Long followId);
}
