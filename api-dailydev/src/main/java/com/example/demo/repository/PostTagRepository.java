package com.example.demo.repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostTag;
import com.example.demo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {


    List<PostTag> findAllByPostId(Long post);

    List<PostTag> findAllByTagId(Long tag);

}
