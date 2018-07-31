package com.itheima.app_2service;

import com.itheima.app_3dao.Dao;
import com.itheima.app_4domain.Student;
import com.itheima.app_4domain.User;

import java.sql.SQLException;
import java.util.List;

public class Service {
    //用户登录验证
    public boolean loginByNameAndPassword(String username, String password) throws SQLException {
        return new Dao().loginByNameAndPassword(username,password);
    }
    //验证用户名是否重复
    public boolean findUserByName(String key) throws SQLException {
        return new Dao().findUserByName(key);
    }
    //注册用户
    public boolean registerUser(User u) throws SQLException {
        return new Dao().registerUser(u);
    }
    //分页查询
    public List<Student> findStudentByPages(String page, String rows) throws SQLException {
        return new Dao().findStudentByPages(page,rows);
    }
    //查询总数
    public int findCount() throws SQLException {
        return new Dao().findCount();
    }
    //添加
    public void add(Student stu) throws SQLException {
        new Dao().add(stu);
    }
}
