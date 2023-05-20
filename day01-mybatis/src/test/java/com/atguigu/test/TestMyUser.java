package com.atguigu.test;

import com.atguigu.bean.User;
import lombok.extern.slf4j.Slf4j;
import com.atguigu.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/11/2023 6:39 PM
 */
@Slf4j
public class TestMyUser {
    //声明一个sqlSession对象
    SqlSession sqlSession;
    @Before
    public void before() throws IOException {
        //建立数据库连接
        String config = "mybatis-config.xml";
        //读取mybatis核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream(config);
        //创建工厂对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //创建sqlSession的工厂
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsStream);
        //开启自动提交事务
      sqlSession = factory.openSession(true);
        // factory.openSession(); 空参构造方法默认不开启自动提交事务
    }

    @After
    public void after(){
        //如果不开启自动提交事务,那么要手动提交事务
        // sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
    }

    //根据id获取用户
    @Test
    public void test1(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // MyUser user = sqlSession.getUserById(1);
        System.out.println(1);
        User userById = mapper.getUserById(1);
        System.out.println(sqlSession);
        // Object o = sqlSession.selectOne("com.atguigu.mapper.UserMapper.getUserById", 1);

    }
    //获取所有用户
    @Test
    public void test2(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        ArrayList<User> users = (ArrayList<User>) mapper.getUser();
        users.forEach(System.out::println);
        log.info(users.toString());
    }
    //插入用户
    @Test
    public void test3(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(null,"jack","12345",22,"女","test@hotmail.com");
        int i = mapper.addUser(user);
        System.out.println(user.getId());
    }
    //插入用户并获取自增长的key
    @Test
    public void test4(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(null,"mary","12345",22,"女","test@hotmail.com");
        int i = mapper.addUserGetKey(user);
        System.out.println("插入用户后生成的key是:"+user.getId());
    }
    //移除用户
    @Test
    public void test5(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.removeUserById(3);
        System.out.println(i);
    }
    //修改用户1
    @Test
    public void test6(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(3,"tom","12345",22,"女","test@hotmail.com");
        int i = mapper.editUser(user);
        log.info(Integer.valueOf(i).toString());
    }
    //修改用户2
    @Test
    public void test7(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.editUserByParams("wong","123",30,"女","test@hotmail.com",3);
    }
    //不使用接口和代理对象,直接使用sqlSession.select方法
    @Test
    public void test8(){
        User user1 = sqlSession.selectOne("com.atguigu.mapper.UserMapper.getUserById", 1);
        System.out.println(user1);
        List<Object> list = sqlSession.selectList("com.atguigu.mapper.UserMapper.getUser");
        list.forEach(System.out::println);
    }
    //测试使用map集合传参
    @Test
    public void test9(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map = new HashMap<>();
        map.put("name","jack001");
        map.put("passd","123");
        map.put("ages",22);
        map.put("gender","男");
        map.put("email","test@hotmail.com");
        map.put("id",1);
        int i = mapper.updateUserByMap(map);
       /* Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            log.info(entry.getKey()+entry.getValue());
        }*/
    }
    @Test
    public void test10(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map = new HashMap<>();
        map.put("name","jack005");
        map.put("passd","123");
        map.put("ages",22);
        map.put("gender","男");
        map.put("email","test@hotmail.com");
        int i = mapper.addUserByMap(map);
       /* Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            log.info(entry.getKey()+entry.getValue());
        }*/
    }
}
