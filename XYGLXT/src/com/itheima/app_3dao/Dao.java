package com.itheima.app_3dao;

import com.itheima.app_4domain.Student;
import com.itheima.app_4domain.User;
import com.itheima.app_5utils.MyC3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class Dao {
    //用户登录验证
    public boolean loginByNameAndPassword(String username, String password) throws SQLException {
        QueryRunner qr = new QueryRunner(MyC3P0Utils.getDataSource());
        String sql = "select * from user where username=? and password=?";
        Object[] o = qr.query(sql, new ArrayHandler(), username, password);
        return o != null;
    }
    //验证用户名是否重复
    public boolean findUserByName(String key) throws SQLException {
        QueryRunner qr = new QueryRunner(MyC3P0Utils.getDataSource());
        String sql = "select * from user where username=?";
        Object[] o = qr.query(sql, new ArrayHandler(), key);
        return o != null;
    }
    //注册用户
    public boolean registerUser(User u) throws SQLException {
        QueryRunner qr = new QueryRunner(MyC3P0Utils.getDataSource());
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        Object[] param = {u.getUsername(),u.getPassword(),u.getEmail(),u.getSex(),u.getBirthday(),u.getPhone()};
        int i = qr.update(sql, param);
        return i == 1;
    }
    //分页查询
    public List<Student> findStudentByPages(String page, String rows) throws SQLException {
        QueryRunner qr = new QueryRunner(MyC3P0Utils.getDataSource());
        String sql = "select * from student limit ?,?";
        Integer p = Integer.parseInt(page);
        Integer r = Integer.parseInt(rows);
        return qr.query(sql, new BeanListHandler<Student>(Student.class),(p-1)*r,r);
    }
    //查询总数
    public int findCount() throws SQLException {
        QueryRunner qr = new QueryRunner(MyC3P0Utils.getDataSource());
        String sql = "select count(*) from student";
        Long l  = (Long)qr.query(sql, new ScalarHandler());
        return l.intValue();
    }
    //添加
    public void add(Student stu) throws SQLException {
        QueryRunner qr = new QueryRunner(MyC3P0Utils.getDataSource());
        String sql = "insert into student values(null,?,?,?,?,?)";
        qr.update(sql, stu.getStuid(), stu.getStuname(), stu.getStuage(), stu.getStuXL(), stu.getStusex());
    }
}
