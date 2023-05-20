package com.atguigu.mapper;

import com.atguigu.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/11/2023 6:13 PM
 */
public interface UserMapper {
    //声明方法增删改查
    //查询
        //查询单个用户
    User getUserById(Integer id);

        //查询所有用户
    List<User> getUser();

    //删除
    int removeUserById(Integer userId);

    //修改
    int editUser(User user);

    int editUserByParams(String name,String pwd,Integer age,String gender,String email,Integer id);

    //增加
    int addUser(User user);

    //增加用户,再获取自增长key
    int addUserGetKey(User user);

    //增加用户,通过传递map集合来
    int updateUserByMap(Map<String,Object> map);

    int addUserByMap(Map<String,Object> map);
}
