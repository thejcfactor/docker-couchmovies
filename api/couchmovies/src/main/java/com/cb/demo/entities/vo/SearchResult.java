package com.cb.demo.entities.vo;

import com.cb.demo.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SearchResult implements Serializable {

    private Movie movie;
    private QueryStats stats;
}
