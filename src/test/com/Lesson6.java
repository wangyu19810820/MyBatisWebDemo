package com;

import com.config.SpringConfig;
import com.dao.UserMapper;
import com.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 通过spring加装mybatis
 * 仍然可以在spring中配置mybatis的配置文件，但是数据源，事务，映射文件一般都在spring中配置
 * mybatis和mybatis-sprin的类库版本要匹配，最好都用最新的稳定版本
 * 因为扫描映射类（Mapper类）处于spring加载的时期，会导致自动映射properties特性失效。
 * 一般改为延迟加载，指定sqlSessionFactoryBeanName而非sqlSessionFactoryBean
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
//@ContextConfiguration("classpath:spring.xml")
public class Lesson6 {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        List<User> users = userMapper.selectAll();
        users.forEach(System.out::println);
    }

}
