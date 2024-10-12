package com.example.demo.service;

import com.example.demo.entity.FollowTag;
import com.example.demo.repository.FollowTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowTagService {

    @Autowired
    private FollowTagRepository repository;

    //    Lấy danh sách tag được theo dõi của user
    public List<FollowTag> getAllTagByUserId(Long userId) {
        return repository.findAllByAccount_Id(userId);
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
