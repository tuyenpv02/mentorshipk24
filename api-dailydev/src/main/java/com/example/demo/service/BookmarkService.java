package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Bookmark;
import com.example.demo.entity.Post;
import com.example.demo.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository repository;

    public List<Bookmark> getAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }
    public Bookmark findById(Long id) {
        Optional<Bookmark> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }
    public Bookmark add(Bookmark Bookmark) {
        return repository.save(Bookmark);
    }

    public Bookmark update(Long id, Bookmark newBookmark) {
        Optional<Bookmark> optional = repository.findById(id);
        return optional.map(o -> {
            o.setPost(Post.builder().id(newBookmark.getPost().getId()).build());
            o.setAccount(Account.builder().id(newBookmark.getAccount().getId()).build());
            return repository.save(o);
        }).orElse(null);
    }

    public Bookmark deleteById(Long id) {
        Optional<Bookmark> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
