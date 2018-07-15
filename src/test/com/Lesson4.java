package com;


import com.model.College;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

// 动态sql，动态组装sql。
// 包含if,choose,trim,where,set,foreach
// sql标签可复用sql片段
// bind标签可从OGNL表达式中创建一个变量并将其绑定到上下文
// 支持多数据库
// 批量更新，一种是组装批量更新的sql，另一种是openSession(ExecutorType.BATCH)
public class Lesson4 {

    // sql标签可复用sql片段
    @Test
    public void test1() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            College college = sqlSession.selectOne("com.dao.CollegeMapper.selectCollegeById",
                    11);
            System.out.println(college);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 动态sql，<where>标签可自动修剪多余的and和or
    // bind可以从OGNL表达式中创建一个变量并将其绑定到上下文
    @Test
    public void test2() {
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

    // 动态sql，<choose>标签，用于多项分支选择
    @Test
    public void test3() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            College param = new College();
            param.setName("a");
            param.setId(3);

            List<College> users = sqlSession.selectList("com.dao.CollegeMapper.selectCollegeBySearch1",
                    param);
            users.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 动态sql，<trim>标签，用于修剪sql，添加"where"前缀，去除多余的"and"
    @Test
    public void test4() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            College param = new College();
            param.setName("a");
            param.setId(3);

            List<College> users = sqlSession.selectList("com.dao.CollegeMapper.selectCollegeBySearch2",
                    param);
            users.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // <selectKey>获取序列用于主键
    // _databaseId可用于检测数据库类别，也可由Configuration获知当前databaseId
    // 要在配置文件中配置databaseIdProvider
    @Test
    public void test5() {
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

    // <set>标签，用在update语句中，可以去除多余的分隔符","
    @Test
    public void test6() {
        try {
            College college = new College();
            college.setId(1);
            college.setName("chemistry");
            college.setLocation("beijing");

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            sqlSession.insert("com.dao.CollegeMapper.updateById", college);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 批量更新，
    // 一种是sql中用<foreach>组装批量更新的sql
    // 一种是openSession(ExecutorType.BATCH);
    @Test
    public void test7() {
        try {
            College college1 = new College();
            college1.setId(16);
            college1.setName("chemistry");
            college1.setLocation("beijing");

            College college2 = new College();
            college2.setId(17);
            college2.setName("chemistry");
            college2.setLocation("beijing");

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
            String databaseId = sqlSession.getConfiguration().getDatabaseId();

            sqlSession.insert("com.dao.CollegeMapper.insertBatch", Arrays.asList(college1, college2));
            sqlSession.insert("com.dao.CollegeMapper.updateById", college1);

            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
