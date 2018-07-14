package com.model;

import lombok.Data;

@Data
public class User {

    public static int UserTypeStudent = 0;
    public static int UserTypeTeacher = 1;

    private Integer id;

    private String name;

    // 用户类型
    private int userType;

    // 学号，用户类型为学生时，才有学号
    private String studentNumber;

    // 教工编号，用户类型为教师时，才有教工编号
    private String teacherNumber;

}
