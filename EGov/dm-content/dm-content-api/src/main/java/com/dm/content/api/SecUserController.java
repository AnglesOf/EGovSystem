package com.dm.content.api;

import com.dm.base.utils.alterResult;
import com.dm.content.model.po.SectionUser;
import com.dm.content.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/secUser")
public class SecUserController {
    @Autowired
    public SecUserService secUserService;

    @RequestMapping("/loginSecUser")
    public ModelAndView loginUser(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        int loginState = secUserService.checkLogin(phone, password);
        if (loginState == 1) {
            SectionUser secUser = secUserService.findUserByPhone(phone);
            List<Cookie> cookie = new ArrayList<>();
            cookie.add(new Cookie("userId", secUser.getId() + ""));
            cookie.add(new Cookie("userName", secUser.getName() + ""));
            cookie.add(new Cookie("userPhone", secUser.getPhone() + ""));
            cookie.add(new Cookie("userPassword", secUser.getPassword() + ""));
            cookie.add(new Cookie("userSex", secUser.getSex() + ""));
            cookie.add(new Cookie("userSection", secUser.getSection() + ""));
            for (Cookie cookie1 : cookie) {
                //设置10分钟有效期
                cookie1.setMaxAge(60 * 60 * 10);
                //设置有效路径
                cookie1.setPath(request.getContextPath());
                response.addCookie(cookie1);
            }
            //把user存放在session中
            HttpSession session = request.getSession();
            session.setAttribute("user", secUser);

            mv.addObject("user", secUser);
            mv.setViewName("redirect:/index3.html");
        } else if (loginState == 0) {
            alterResult.tanChuang("用户不存在", "login.html", mv);
        } else {
            alterResult.tanChuang("用户被封禁", "login.html", mv);
        }
        return mv;
    }
}
