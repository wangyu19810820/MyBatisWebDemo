package com;

import com.model.College;
import com.model.Score;
import com.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * 一对一的写法和一对多的写法
 */
public class Lesson3 {

    // 添加学院信息，和学生信息，成绩信息
    @Test
    public void test1() {
        try {
            College college = new College();
            college.setName("art");

            User user = new User();
            user.setName("user1");
            user.setCollege(college);

            Score score1 = new Score();
            score1.setName("Lesson1");
            score1.setScore(99);
            score1.setUser(user);

            Score score2 = new Score();
            score2.setName("Lesson2");
            score2.setScore(88);
            score2.setUser(user);

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            sqlSession.insert("com.dao.CollegeMapper.insertCollege", college);
            sqlSession.insert("com.dao.UserMapper.insertUserAndCollege", user);
            sqlSession.insert("com.dao.ScoreMapper.insertScore", score1);
            sqlSession.insert("com.dao.ScoreMapper.insertScore", score2);

            sqlSession.commit();

            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 一对一
    @Test
    public void test2() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<User> users = sqlSession.selectList("com.dao.UserMapper.selectUserAndCollege");
            users.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 单条sql组装所有一对一，一对多数据
    @Test
    public void test3() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<User> users = sqlSession.selectList("com.dao.UserMapper.selectUserAndCollegeScore");
            users.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 单条sql组装所有一对一，一对多数据。在应用分页的时候，会有数量bug
    @Test
    public void test4() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            RowBounds rowBounds = new RowBounds(1, 1);
            List<User> users = sqlSession.selectList("com.dao.UserMapper.selectUserAndCollegeScore",
                    null, rowBounds);
            users.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据用户id查询成绩列表
    @Test
    public void test5() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<Score> scoreList = sqlSession.selectList("com.dao.ScoreMapper.selectScoreByUserId",
                    3);
            scoreList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 多条sql，组装一对一，一对多数据，无分页的数量bug
    @Test
    public void test6() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            RowBounds rowBounds = new RowBounds(0, 1);
            List<User> users = sqlSession.selectList("com.dao.UserMapper.selectUserAndCollegeScoreByLazy",
                    null, rowBounds);
            users.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 通过变换sql写法，将分页参数传入xml中，解决分页数量不正确的问题
    @Test
    public void test7() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            HashMap params = new HashMap();
            params.put("pageSize", 1);
            params.put("pageNumber", 1);
            List<User> users = sqlSession.selectList("com.dao.UserMapper.selectUserAndCollegeScoreByPage",
                    params);
            users.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
