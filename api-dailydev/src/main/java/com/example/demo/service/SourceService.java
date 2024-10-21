package com.example.demo.service;

import com.example.demo.entity.Source;
import com.example.demo.entity.Vote;

import java.util.List;

public interface SourceService {

    List<Source> getAll();

    Source add(Source source);

    Boolean existsById(Long id);
}
