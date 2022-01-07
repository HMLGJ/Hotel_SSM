package com.hotel.interceptor;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.hotel.utils.SystemConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断session是否为空
        if(request.getSession().getAttribute(SystemConstant.LOGINUSER)==null){
            //如果没有登录，则去到登录页面
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;//验证失败，进行拦截
        }
        //验证通过
        return true;
    }
}
