package com.cb.demo.services;

import java.util.List;

import com.cb.demo.entities.vo.Result;

public interface SearchService {

    Result searchQuery(String query, String genres, Boolean fuzzy);
    List<String> autocomplete(String query);

}