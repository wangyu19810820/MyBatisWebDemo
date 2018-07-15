package com;

import com.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Lesson2 {


    // 新增教师
    @Test
    public void test1() {
        try {
            User user = new User();
            user.setName("teaName1");
            user.setUserType(User.UserTypeTeacher);
            user.setTeacherNumber("tea1111");

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("mapper.UserMapper.insertTeacher", user);
            sqlSession.commit();

            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 新增学生
    @Test
    public void test2() {
        try {
            User user = new User();
            user.setUserType(User.UserTypeStudent);
            user.setName("stuName1");
            user.setStudentNumber("stu11111");

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("mapper.UserMapper.insertStudent", user);
            sqlSession.commit();

            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<User> users = sqlSession.selectList("mapper.UserMapper.selectAllUser");
            users.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
