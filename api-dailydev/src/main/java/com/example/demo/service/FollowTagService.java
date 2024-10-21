package com.example.demo.service;

import com.example.demo.entity.FollowTag;

import java.util.List;

public interface FollowTagService {

    List<FollowTag> getAllTagByUserId(Long userId);

    List<FollowTag> getAll();

    FollowTag add(FollowTag followTag);

    FollowTag deleteById(Long id);

    Boolean existsById(Long id);
}
