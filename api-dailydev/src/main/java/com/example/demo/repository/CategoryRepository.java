package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    Optional<Category> findByNameAndSource(String name, Source source);

    Optional<Category> findByLink(String link);

    List<Category> findAllBySource(Source source);
}
