package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAccount_Id(Long userId);

    List<Post> findAllByCategory_Id(Long categoryId);

    Optional<Post> findByLink(String link);
}
