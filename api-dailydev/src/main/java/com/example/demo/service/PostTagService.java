package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostTag;
import com.example.demo.entity.Tag;

import java.util.List;

public interface PostTagService {

    List<PostTag> getAllPostTagByByTag(Tag tag);

    List<PostTag> getAllPostTagByByPost(Post post);


    PostTag add(PostTag postTag);

    PostTag deleteById(Long id);

    Boolean existsById(Long id);
}
