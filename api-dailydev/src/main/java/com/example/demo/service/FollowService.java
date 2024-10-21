package com.example.demo.service;

import com.example.demo.entity.Follow;

import java.util.List;

public interface FollowService {

    //    Lấy danh sách người được theo dõi của user
    List<Follow> getAllUser(Long following);

    //    Lấy danh sách người đang theo dõi user
    List<Follow> getAllFollow(Long followId);

    Follow add(Follow follow);

    Follow deleteById(Long id);

    Boolean existsById(Long id);
}
