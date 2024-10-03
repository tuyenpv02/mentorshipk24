package com.example.demo.service;

import com.example.demo.entity.Follow;
import com.example.demo.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    @Autowired
    private FollowRepository repository;

    //    Lấy danh sách người được theo dõi của user
    public List<Follow> getAllUser(Long following) {
        return repository.findAllByUserId(following);
    }

    //    Lấy danh sách người đang theo dõi user
    public List<Follow> getAllFollow(Long followId) {
        return repository.findAllByFollowId(followId);
    }

    public Follow add(Follow Follow) {
        return repository.save(Follow);
    }

    public Follow deleteById(Long id) {
        Optional<Follow> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
