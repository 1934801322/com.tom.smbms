package com.tom.servlet.user;

import com.mysql.jdbc.StringUtils;
import com.tom.pojo.User;
import com.tom.service.user.UserServicelmpl;
import com.tom.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//实现Serlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd")&& method != null){
            this.updatePwd(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req, HttpServletResponse resp){
        //从Session里面那id；
        Object o= req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");

        boolean flag=false;
        if (o !=null && !StringUtils.isNullOrEmpty(newpassword)){
            UserServicelmpl userservice = new UserServicelmpl();
            flag = userservice.updatePwd(((User) o).getId(), newpassword);
            if (flag){
                req.setAttribute("message","修改密码成功，请重新登录");
                //密码修改成功，移除当前Session
                req.getSession().removeAttribute(Constants.USER_SESSION);
                try {
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else {
                req.setAttribute("message","修改密码失败");
            }
        }else {
            req.setAttribute("message","新密码有问题");
        }
        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
