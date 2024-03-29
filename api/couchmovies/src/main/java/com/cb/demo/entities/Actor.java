package com.cb.demo.entities;

import com.couchbase.client.java.repository.annotation.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor implements Serializable {

    private Long id;
    private String name;
    private Long order;
    private String character;
    @Field("cast_id")
    private Long castId;
    @Field("credit_id")
    private String creditId;
    @Field("profile_path")
    private String profilePath;
    private String gender;
}
