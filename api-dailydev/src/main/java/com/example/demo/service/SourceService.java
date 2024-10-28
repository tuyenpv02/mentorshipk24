package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Source;
import com.example.demo.entity.Vote;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SourceService {

    List<Source> getAll();
    Page<Source> getAll(int page, int size);

    Source add(Source source);

    Boolean existsById(Long id);
}
