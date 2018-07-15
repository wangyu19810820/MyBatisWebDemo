package com.model;

import lombok.Data;

@Data
public class Score {

    private Integer id;
    private String name;
    private double score;
    private User user;

}
