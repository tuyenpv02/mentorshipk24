package com.example.demo.service.impl;

import com.example.demo.dto.response.PageResponse;
import com.example.demo.entity.Account;
import com.example.demo.entity.Bookmark;
import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import jakarta.persistence.criteria.Predicate;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;


    public List<Post> getAll() {
        return repository.findAll();
    }

    public Page<Post> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        return repository.findAll(pageable);
    }

    public PageResponse<Post> getAll(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        Page<Post> postPage = repository.findByKeyword("%" + keyword + "%", pageable);

        PageResponse<Post> response = PageResponse.<Post>builder()
                .pageNumber(postPage.getNumber())
                .size(postPage.getSize())
                .totalPages(postPage.getTotalPages())
                .totalElements(postPage.getTotalElements())
                .data(postPage.getContent())
                .build();
        return response;
    }

    public PageResponse<Post> getAllByUserId(Long userId, int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.Direction.DESC, "id");
        Page<Post> postPage = repository.findAllByAccountId(userId, pageable);

        PageResponse<Post> response = PageResponse.<Post>builder()
                .pageNumber(postPage.getNumber())
                .size(postPage.getSize())
                .totalPages(postPage.getTotalPages())
                .totalElements(postPage.getTotalElements())
                .data(postPage.getContent())
                .build();
        return response;
    }

    public PageResponse<Post> getAllByCategoryId(Long cateId, int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.Direction.DESC, "id");
        Page<Post> postPage = repository.findAllByCategoryId(cateId, pageable);

        PageResponse<Post> pageResponse = PageResponse.<Post>builder()
                .pageNumber(postPage.getNumber())
                .size(postPage.getSize())
                .totalPages(postPage.getTotalPages())
                .totalElements(postPage.getTotalElements())
                .data(postPage.getContent())
                .build();
        return pageResponse;
    }

    public Post findByName(String name) {
        Optional<Post> optional = repository.findByTitle(name);
        return optional.map(o -> o).orElse(null);
    }

    public Optional<Post> findByGuId(String guId) {
        return repository.findByGuId(guId);
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

    public Post save(Post post) {
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

    public Boolean deleteById(Long id) {
        Optional<Post> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return true;
        }).orElse(false);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
