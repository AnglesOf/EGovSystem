package com.dm.content.interceptor;

import com.dm.base.utils.cookieUtil;
import com.dm.content.model.po.GovUser;
import com.dm.content.model.po.SectionUser;
import com.dm.content.model.po.User;
import com.dm.content.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    public RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        Cookie[] cookies = request.getCookies();
        //找出自动登录的cookie
        Cookie cookie1= cookieUtil.findCookie(cookies, "userId");
        if (user == null) {
            //第一次登录
            if (cookie1 != null) {
                String userId = cookieUtil.findCookie(cookies, "userId").getValue();
                String userName = cookieUtil.findCookie(cookies, "userName").getValue();
                String userPhone = cookieUtil.findCookie(cookies, "userPhone").getValue();
                String userPassword = cookieUtil.findCookie(cookies, "userPassword").getValue();
                String userSex = cookieUtil.findCookie(cookies, "userSex").getValue();
                Cookie userFlagCookie = cookieUtil.findCookie(cookies, "userFlag");
                if (userFlagCookie==null) { // 部门职员
                    String userSection = cookieUtil.findCookie(cookies, "userSection").getValue();
                    request.getSession().setAttribute("user", new SectionUser(userId, userName, userPhone, userPassword, userSex, userSection));
                } else if(userFlagCookie.getValue().equals("0")){ // 普通用户
                    String userState = cookieUtil.findCookie(cookies, "userState").getValue();
                    request.getSession().setAttribute("user", new User(Integer.parseInt(userId), userName, userPhone, userPassword, Integer.parseInt(userFlagCookie.getValue()), userSex, Integer.parseInt(userState)));
                } else {  // 政府职员
                    request.getSession().setAttribute("user", new GovUser(Integer.parseInt(userId), userName, userPhone, userPassword, Integer.parseInt(userFlagCookie.getValue()), userSex));
                }
                //不是第一次登录，验证userName 和 userPassword
                //放行
            }else {
                if (request.getHeader("x-requested-with")!=null) {
//                    response.sendRedirect("/EGov/login.html");
                    response.setHeader("CONTEXTPATH", "/EGov/login.html");
                }else
                    response.sendRedirect("/EGov/login.html"); //重定向地址
                return false;
            }
        }
        redisService.set("visitCount", (Integer.parseInt(redisService.get("visitCount"))+1) + "");
        return true;
    }

}
