package com;

import com.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 通过MyBatis原生方式，加载配置文件，新增数据，获取查询内容
 */
public class Lesson1 {

    @Test
    public void test() {
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:file:~/sample.db";
        String user = "sa";
        String password = "";
        try {
            org.h2.tools.Server.createTcpServer().start();
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
//            boolean b = conn.createStatement().execute("CREATE TABLE `users` (" +
//                    "`username`  varchar(10) ," +
//                    "`password`  varchar(10) ," +
//                    "`enabled`  int " +
//                    ")");
            boolean b1 = conn.createStatement().execute("insert into TUser (id, name, sex) values (null, '1111', 1)");
            System.out.println(b1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 新增数据，获取主键
    @Test
    public void test1() {
        try {
            User user = new User();
            user.setName("user1");

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("mapper.UserMapper.insert", user);
            sqlSession.commit();

            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 查询数据
    @Test
    public void test2() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Object users = sqlSession.selectList("mapper.UserMapper.selectAll");
            System.out.println(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
