package com.example.demo.service.impl;

import com.example.demo.dto.response.PageResponse;
import com.example.demo.entity.Account;
import com.example.demo.entity.Bookmark;
import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.repository.BookmarkRepository;
import com.example.demo.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkRepository repository;

    public List<Bookmark> getAll() {
        return repository.findAll();
    }

    public PageResponse<Bookmark> getAllByUserId(Long userId, int pageNumber, int size) {

        Pageable pageable = PageRequest.of(pageNumber, size, Sort.Direction.DESC, "id");
        Page<Bookmark> bookmarkPage = repository.findByAccountId(userId, pageable);

        PageResponse<Bookmark> response = PageResponse.<Bookmark>builder()
                .pageNumber(bookmarkPage.getNumber())
                .size(bookmarkPage.getSize())
                .totalPages(bookmarkPage.getTotalPages())
                .totalElements(bookmarkPage.getTotalElements())
                .data(bookmarkPage.getContent())
                .build();

        return response;
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
