package com.atguigu.servlet;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.servlet.base.MethodBaseServlet;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/26/2023 7:05 PM
 */
@WebServlet("/user")
public class UserServlet extends MethodBaseServlet {
    private UserService service = new UserServiceImpl();
    private Gson gson = new Gson();
    private void toLoginPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processTemplate("user/login",req,resp);
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
         String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = service.login(username, password);
        if(user != null){
            //登录成功的话需要设置session,把用户名传过去
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            resp.sendRedirect(req.getContextPath()+"/user?flag=loginSuccess");
        }else{
            String errMessage = "用户名或密码错误";
            req.getServletContext().setAttribute("errMessage",errMessage);
            // resp.sendRedirect(req.getContextPath()+"/user?flag=login");
            resp.sendRedirect(req.getContextPath()+"/user?flag=toLoginPage");
        }
    }
    private void loginSuccess(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*
        有了session之后,就可以不用通过请求域来获取数据了
        //要设置属性,传名字过去,因为要显示欢迎谁登录
        req.setAttribute("username",username);*/
        processTemplate("user/login_success",req,resp);
    }
    private void toRegistryPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processTemplate("user/regist",req,resp);
    }
    private void registry(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf8");
        User user = new User();
        Map<String, String[]> map = req.getParameterMap();
        /*使用ajax之后可以异步请求校验验证码,不再需要在这个地方来验证
        //增加验证码校验的页面
        String code2 = req.getParameter("verifyCode");

        String code1 = (String)session.getAttribute("KAPTCHA_SESSION_KEY");*/
        HttpSession session = req.getSession();
            try {
                BeanUtils.populate(user,map);
            } catch (IllegalAccessException |InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            try {
                boolean success = service.registry(user);
                if(success){
                    //将username设置到session里面,注册成功后可以直接在session里面拿取数据
                    session.setAttribute("username",user.getUsername());
                    resp.sendRedirect(req.getContextPath()+"/user?flag=registrySuccess");
                }
            } catch (SQLException | IOException e) {
                try {
                    resp.getWriter().write("注册失败,请重新<a href='"+req.getContextPath()+"/user?flag=toRegistryPage'>注册</a>");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                e.printStackTrace();
            }



    }
    private void registrySuccess(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       processTemplate("user/regist_success",req,resp);
    }
    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        processTemplate("../index",req,resp);
    }
    //checkUsername使用ajax实现用户名校验的功能
    private void checkUsername(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        //获取前端ajax异步请求发送过来的用户名
        String username = req.getParameter("username");

        // 根据发送过来的用户名查询用户
        User user = service.getUserByUsername(username);
        //创建map集合,用来存放查询的结果
        Map<String,Boolean> map = new HashMap<>();
        //根据用户名是否为空来判断map集合中的value,也就是用户名是否可用
        if(user == null){
            map.put("available",true);
        }else{
            map.put("available",false);
        }
        //将map集合转换为JSON字符串,方便下一步发送给前端
        String s = gson.toJson(map);
        //将转换好的字符串发送给前端
        resp.getWriter().write(s);
    }
    //验证码输入之后通过ajax异步请求校验输入是否OK
    private void checkCode(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //获取ajax传送过来的验证码
        String code = req.getParameter("code");
        // 获取kaptchaServlet生成的验证码
            //先获取session
        HttpSession session = req.getSession();
            //拿取session里面key为KAPTCHA_SESSION_KEY的value,这个就是生成的验证码的内容
        String kaptchaCode = (String)session.getAttribute("KAPTCHA_SESSION_KEY");

        Map<String,Boolean> map = new HashMap<>();

        //对比用户输入的验证码和kaptcha生成的验证码是否相同
        if(kaptchaCode.equals(code)){
            //如果相同,返回true
            map.put("codeResult",true);
        }else{
            //否则,返回false
            map.put("codeResult",false);
        }
        //将map转换成json对象,然后返回给前端
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }
}
