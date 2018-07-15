package com.model;

import com.enumerate.ScoreGradeEnum;
import com.enumerate.ScoreTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Score extends BaseModel {

    private Integer id;
    private String name;
    private double score;
    private User user;

    private ScoreTypeEnum scoreType;
    private ScoreGradeEnum scoreGrade;

    private LocalDateTime addTime;

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", user=" + user +
                ", scoreType=" + scoreType +
                ", scoreGrade=" + scoreGrade +
                ", addTime=" + addTime +
                ", addDttm=" + addDttm +
                ", addUser='" + addUser + '\'' +
                ", lastUpdDttm=" + lastUpdDttm +
                ", lastUpdUser='" + lastUpdUser + '\'' +
                "} " + super.toString();
    }
}
