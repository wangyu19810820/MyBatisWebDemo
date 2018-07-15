package com;


import com.dao.UserMapper;
import com.model.College;
import com.model.Score;
import com.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class Lesson4 {

    @Test
    public void test1() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            College param = new College();
            param.setName("a");
            param.setId(3);

            List<College> users = sqlSession.selectList("com.dao.CollegeMapper.selectCollegeBySearch",
                    param);
            users.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            College college = new College();
            college.setName("chemistry");
            college.setLocation("nanjing");

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            String databaseId = sqlSession.getConfiguration().getDatabaseId();

            sqlSession.insert("com.dao.CollegeMapper.insertCollege", college);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            College college = new College();
            college.setId(1);
            college.setName("chemistry");
            college.setLocation("beijing");

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            String databaseId = sqlSession.getConfiguration().getDatabaseId();

            sqlSession.insert("com.dao.CollegeMapper.updateById", college);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
