package com.dm.content.api;


import com.dm.base.utils.alterResult;
import com.dm.content.model.po.GovUser;
import com.dm.content.model.po.User;
import com.dm.content.service.GovUserService;
import com.dm.content.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/govUser")
public class GovUserController {
    @Autowired
    public GovUserService govUserService;

    @Autowired
    public UserService userService;

    @RequestMapping("/loginGovUser")
    public ModelAndView loginGovUser(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        int loginState = govUserService.checkLogin(phone, password);
        if (loginState == 1) {
            GovUser govUser = govUserService.findGovUserByPhone(phone);
            List<Cookie> cookie = new ArrayList<>();
            cookie.add(new Cookie("userId", govUser.getId() + ""));
            cookie.add(new Cookie("userName", govUser.getName() + ""));
            cookie.add(new Cookie("userPhone", govUser.getPhone() + ""));
            cookie.add(new Cookie("userPassword", govUser.getPassword() + ""));
            cookie.add(new Cookie("userFlag", govUser.getFlag() + ""));
            cookie.add(new Cookie("userSex", govUser.getSex() + ""));
            for (Cookie cookie1:cookie){
                //设置10分钟有效期
                cookie1.setMaxAge(60 * 60 * 10);
                //设置有效路径
                cookie1.setPath(request.getContextPath());
                response.addCookie(cookie1);
            }
            //把user存放在session中
            HttpSession session = request.getSession();
            session.setAttribute("user", govUser);

            mv.addObject("user", govUser);
            mv.setViewName("redirect:/index.html");
        } else if (loginState == 0) {
            alterResult.tanChuang("用户不存在", "login.html", mv);
        } else {
            alterResult.tanChuang("密码错误", "login.html", mv);
        }
        return mv;
    }

    @RequestMapping("/removeUser")
    public ModelAndView removeUser(int id){
        ModelAndView mv = new ModelAndView();
        userService.deleteUserById(id);
        mv.setViewName("redirect:/govUserHTML/ListUsers.html");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/modifyUserState")
    public User closeUser(int id){
        int state = userService.findUserState(id);
        if(state==0){
            userService.closeUserById(id);
        }else{
            userService.openUserById(id);
        }
        return userService.findUserById(id);
    }

}
