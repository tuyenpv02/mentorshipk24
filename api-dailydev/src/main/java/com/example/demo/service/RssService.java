package com.example.demo.service;

import com.example.demo.dto.RssRequest;
import com.example.demo.entity.Category;

import java.util.List;

public interface RssService {

    // get lst category, link in url
    List<Category> readSource(String url);

    // get lst post
    String fetchRss(RssRequest rssRequest) throws Exception;
}
