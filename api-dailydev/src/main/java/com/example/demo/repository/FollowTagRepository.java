package com.example.demo.repository;

import com.example.demo.entity.FollowTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowTagRepository extends JpaRepository<FollowTag, Long> {


    List<FollowTag> findAllByUserId(Long userId);
}
