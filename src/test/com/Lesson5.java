package com;

import com.enumerate.ScoreGradeEnum;
import com.enumerate.ScoreTypeEnum;
import com.model.Score;
import com.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

// 处理枚举采用默认EnumTypeHandler，EnumOrdinalTypeHandler
// EnumTypeHandler在数据库中存储枚举的字符值，EnumOrdinalTypeHandler存储枚举的索引值
// 自定义类型处理器处理LocalDateTime和Timestamp之间的转换
// 都在配置文件中配置
public class Lesson5 {

    @Test
    public void test1() {
        try {
            Score score = new Score();
            score.setName("lesson2");
            score.setScoreType(ScoreTypeEnum.EXAM);
            score.setScoreGrade(ScoreGradeEnum.B);
            score.setAddTime(LocalDateTime.now());
            User user = new User();
            user.setId(1);
            score.setUser(user);

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            sqlSession.insert("com.dao.ScoreMapper.insertScore", score);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            Score score = new Score();
            score.setName("lesson1");
            score.setScore(97);
            score.setScoreType(ScoreTypeEnum.EXAM);
            score.setScoreGrade(ScoreGradeEnum.B);
            User user = new User();
            user.setId(1);
            score.setUser(user);

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            List<Score> scoreList = sqlSession.selectList("com.dao.ScoreMapper.selectScore");
            scoreList.forEach(System.out::println);

            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
