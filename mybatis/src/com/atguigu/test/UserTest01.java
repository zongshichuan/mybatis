package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserTest01 {

    @Test
    public void queryUserById() throws IOException {
        InputStream inputStream;
        SqlSessionFactory sessionFactory;
        SqlSession sqlSession;
        UserMapper userMapper;
        //1、加载mybatis的核心配置文件
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //2、构建SqlSessionFactory
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3、得到SqlSession，与数据库进行crud的核心对象
        sqlSession = sessionFactory.openSession();
        //4、
        //加载接口
        /**
         * 接口都没有写实现类,但是能够实现对db的查询?
         * UserMapper:代理实现类对象,
         *  jdk动态代理:创建出了一个实现类
         */

        userMapper = sqlSession.getMapper(UserMapper.class);
        //5 执行方法
        User user = userMapper.selectUserById(1);
        System.out.println(user);
        //6、释放
        sqlSession.close();
    }
}
