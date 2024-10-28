package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Page<Bookmark> findByAccountId(Long id, Pageable pageable);

    List<Bookmark> findAllByAccountId(Long userId );
}
