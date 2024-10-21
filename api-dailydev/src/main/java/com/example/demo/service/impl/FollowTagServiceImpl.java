package com.example.demo.service.impl;

import com.example.demo.entity.Comment;
import com.example.demo.entity.FollowTag;
import com.example.demo.repository.FollowTagRepository;
import com.example.demo.service.FollowTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowTagServiceImpl implements FollowTagService {

    @Autowired
    private FollowTagRepository repository;

    //    Lấy danh sách follow tag được theo dõi của user
    public List<FollowTag> getAllTagByUserId(Long userId) {
        return repository.findAllByAccount_Id(userId);
    }

    public List<FollowTag> getAll() {
        return repository.findAll();
    }

    public FollowTag add(FollowTag FollowTag) {
        return repository.save(FollowTag);
    }

    public FollowTag deleteById(Long id) {
        Optional<FollowTag> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
