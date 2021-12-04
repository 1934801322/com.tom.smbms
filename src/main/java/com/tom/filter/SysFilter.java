package com.tom.filter;

import com.tom.pojo.User;
import com.tom.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class SysFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //过滤器，从Session中获取用户
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);

        if (user == null) { //用户Session不存在 已经被移除或者注销或者为登录
            response.sendRedirect("/login.jsp");

        }else {
            filterChain.doFilter(req,resp);
        }

    }

    public void destroy() {

    }
}
