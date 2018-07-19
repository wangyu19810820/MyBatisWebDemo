package com.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 学生
 */
@Data
public class User implements Serializable {

    public static int UserTypeStudent = 0;
    public static int UserTypeTeacher = 1;

    private Integer id;

    private String name;

    private String otherName;

    // 用户类型
    private int userType;

    // 学号，用户类型为学生时，才有学号
    private String studentNumber;

    // 教工编号，用户类型为教师时，才有教工编号
    private String teacherNumber;

    // 所属学院
    private College college;

    // 成绩列表
    private List<Score> scoreList;
}
