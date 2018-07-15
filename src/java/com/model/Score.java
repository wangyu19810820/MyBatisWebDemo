package com.model;

import com.enumerate.ScoreGradeEnum;
import com.enumerate.ScoreTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Score {

    private Integer id;
    private String name;
    private double score;
    private User user;

    private ScoreTypeEnum scoreType;
    private ScoreGradeEnum scoreGrade;

    private LocalDateTime addTime;

}
