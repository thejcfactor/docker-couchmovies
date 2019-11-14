package com.cb.demo.entities.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class Facet implements Serializable {

    private String name;
    private List<FacetItem> items;

}
