package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class UserTest02 {
    InputStream inputStream = null;
    SqlSessionFactory sessionFactory = null;
    SqlSession sqlSession = null;
    UserMapper userMapper = null;

    /*
        JUnit4的@Before：初始化方法  对每一个测试方法都要执行一次。
        JUnit4的@After: 释放资源     对于每一个测试方法都要执行一次。
     */
    //每次需要加载xml,得到sqlSession
    @Before
    public void init() throws IOException {
        //加载mybatis的核心配置文件
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //构建SqlSessionFactory
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //得到SqlSession,与数据库进行crud的核心对象
        //true:表示自动提交事务
        sqlSession = sessionFactory.openSession(true);
        //加载接口，内部原理是jdk的动态代理
        userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("init()  执行了");
    }

    @Test
    public void  queryUserById(){
        /**
         * 注意：在查询中userMapper.xml配置映射的User要和下面介绍这个函数返回值的User是同一种类型
         *
         * 但是修改、插入、删除等不用管是哪一个User都不会影响
         */
        User user = userMapper.selectUserById(2);
        System.out.println(user);
    }

    @Test
    public void queryUserAll(){
        List<User> list = userMapper.queryUserAll();
        System.out.println(list);
    }

    @Test
    public void updateUserById(){
        int result = userMapper.updateUserById(new User(2,"哈哈",0));
//        sqlSession.commit();
        System.out.println(result);
    }

    @Test
    public void deleteUserById(){
        int result = userMapper.deleteUserById(1);
        System.err.println(result);
    }

    @Test
    public void insertUser(){
        User user = new User(null,"传3",1);
        int reslt = userMapper.insertUser(user);
//        com.atguigu.entity.User user = new com.atguigu.entity.User("小马", 1);
        System.out.println(reslt);
        //得到数据的id,再次查询数据
        System.err.println(user.getId());
    }

    @Test
    public void queryUserMapById(){
        Map<Integer,User> map = userMapper.queryUserMapById(6);
        System.err.println(map);
    }


    @After
    public void destory(){
        sqlSession.close();
    }


}
