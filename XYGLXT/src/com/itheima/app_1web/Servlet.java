package com.itheima.app_1web;

import com.itheima.app_2service.Service;
import com.itheima.app_4domain.Student;
import com.itheima.app_4domain.User;
import com.itheima.app_5utils.MyBaseServlet;
import com.itheima.app_5utils.MyBeanUtilsPlus;
import com.itheima.app_5utils.Pager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Servlet",urlPatterns = "/Servlet")
public class Servlet extends MyBaseServlet {
    //用户登录验证
    public String loginByNameAndPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Service se = new Service();
        boolean b = se.loginByNameAndPassword(username,password);
        if (b){
            request.setAttribute("msg",username);
            return "/WEB-INF/stuList.jsp";
        }else{
            request.setAttribute("msg","用户名或密码错误");
            return "/index.jsp";
        }
    }
    //验证用户名是否已经存在
    public void findUserByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String key = request.getParameter("key");
        Service se = new Service();
        boolean b = se.findUserByName(key);
        if (b){
            response.getWriter().print("true");
        }
    }
    //注册用户
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        User u = MyBeanUtilsPlus.populate(User.class, map);
        Service se = new Service();
        boolean b = se.registerUser(u);
        if (b){
            response.getWriter().print(1);
        }else{
            response.getWriter().print(-1);
        }
    }
    //分页查询
    public void findStudentByPages(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");

        Service se = new Service();
        List<Student> list =  se.findStudentByPages(page,rows);
        Pager<Student> p = new Pager<>();
        p.setRows(list);
        int count = se.findCount();
        p.setTotal(count);
        String s = JSONObject.fromObject(p).toString();
        response.getWriter().print(s);
    }
    //添加信息
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        Student stu = new Student();
        BeanUtils.populate(stu,map);
        Service se = new Service();
        se.add(stu);
        response.getWriter().print("添加成功");
    }
}
