package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> getAll() {
        return repository.findAll();
    }

    public List<Post> getAllByUserId(Long userId) {
        return repository.findAllByAccountId(userId);
    }

    public List<Post> getAllByCategoryId(Long cateId) {
        return repository.findAllByCategoryId(cateId);
    }

    public Post findByName(String name) {
        Optional<Post> optional = repository.findByTitle(name);
        return optional.map(o -> o).orElse(null);
    }

    public Post findByGuId(String guId) {
        Optional<Post> optional = repository.findByGuId(guId);
        return optional.map(o -> o).orElse(null);
    }

    public Post findByLink(String link) {
        List<Post> optional = repository.findByLink(link);
        if (optional.size() > 0) {
            return optional.get(0);

        }
        return null;
    }

    public Post findById(Long id) {
        Optional<Post> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }

    public Post add(Post post) {
        return repository.save(post);
    }

    public Post update(Long id, Post newPost) {
        Optional<Post> optional = repository.findById(id);
        return optional.map(o -> {
            o.setTitle(newPost.getTitle());
            o.setDescription(newPost.getDescription());
            o.setLink(newPost.getLink());
            o.setCategory(Category.builder().id(newPost.getCategory().getId()).build());
            o.setAccount(Account.builder().id(newPost.getAccount().getId()).build());
            return repository.save(o);
        }).orElse(null);
    }

    public Post deleteById(Long id) {
        Optional<Post> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
