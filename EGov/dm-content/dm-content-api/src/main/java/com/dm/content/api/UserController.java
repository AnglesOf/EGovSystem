package com.dm.content.api;


import com.dm.base.utils.alterResult;
import com.dm.base.utils.cookieUtil;
import com.dm.content.model.po.User;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("/findUsers")
    public ModelAndView findUsers() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usersList", userService.findAllUser());
        mv.setViewName("forward:/WEB-INF/views/ListUsers.jsp");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/findUserLimit")
    private List<User> findUserLimit(int pageIndex, int pageSize){
        return userService.findUserLimit(pageIndex, pageSize);
    }

    @RequestMapping("/findUserById")
    private ModelAndView findUserById(int id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usersList", userService.findUserById(id));
        mv.setViewName("forward:/WEB-INF/views/ListUsers.jsp");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/findUserByPhone")
    private User findUserByPhone(String phone) {
        return userService.findUserByPhone(phone);
    }

    @ResponseBody
    @RequestMapping("/checkUserByPhone")
    private User checkUserByPhone(String phone) {
        return userService.findUserByPhone(phone);
    }

    @RequestMapping("/loginUser")
    public ModelAndView loginUser(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(phone);
        System.out.println("aaaaaaaaaaaaaaaaaaadawdawdawdawd威风威风威风威风威风威风");
        System.out.println(password);
        ModelAndView mv = new ModelAndView();
        int loginState = userService.checkLogin(phone, password);
        if (loginState == 1) {
            User user = userService.findUserByPhone(phone);
            List<Cookie> cookie  = new ArrayList<>();
            cookie.add(new Cookie("userId", user.getId() + ""));
            cookie.add(new Cookie("userName", user.getName() + ""));
            cookie.add(new Cookie("userPhone", user.getPhone() + ""));
            cookie.add(new Cookie("userPassword", user.getPassword() + ""));
            cookie.add(new Cookie("userFlag", user.getFlag() + ""));
            cookie.add(new Cookie("userSex", user.getSex() + ""));
            cookie.add(new Cookie("userState", user.getState() + ""));
            for (Cookie cookie1:cookie){
                //设置10分钟有效期
                cookie1.setMaxAge(60 * 60 * 10);
                //设置有效路径
                cookie1.setPath(request.getContextPath());
                response.addCookie(cookie1);
            }
            //把user存放在session中
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            mv.addObject("user", user);
            mv.setViewName("redirect:/index2.html");
        } else if (loginState == 0) {
            alterResult.tanChuang("用户不存在", "login.html", mv);
        } else if (loginState == 2){
            alterResult.tanChuang("密码错误", "login.html", mv);
        } else {
            alterResult.tanChuang("用户被封禁", "login.html", mv);
        }
        return mv;
    }


    @RequestMapping("/register")
    public ModelAndView registerUser(String name, String phone, String password, String sex) {
        ModelAndView mv = new ModelAndView();
        userService.addUser(new User(name,phone,password,sex));
        mv.setViewName("redirect:/login.html");
        return mv;
    }

    @RequestMapping("/queryInfo")
    public ModelAndView queryInfoUser(int id) {
        ModelAndView mv = new ModelAndView();
        User user = userService.findUserById(id);
        mv.addObject("user",user);
        mv.setViewName("forward:/WEB-INF/views/userInfoForGov.jsp");
        return mv;
    }

    @RequestMapping("/modifyInfoForGov")
    public ModelAndView modifyInfoForGov(int id, String name, String phone, String sex) {
        ModelAndView mv = new ModelAndView();
        int s = userService.modifyInfo(id, name, phone, sex);
        mv.setViewName("redirect:/govUserHTML/ListUsers.html");
        return mv;
    }

    @RequestMapping("/modifyInfo")
    public ModelAndView modifyInfo(int id, String name, String phone, String sex) {
        ModelAndView mv = new ModelAndView();
        int s = userService.modifyInfo(id, name, phone, sex);
        mv.setViewName("redirect:/userHTML/userInfo.html");
        return mv;
    }

    @RequestMapping("/modifyPsw")
    public ModelAndView modifyPswUser(@RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        Cookie cookie = cookieUtil.findCookie(cookies, "userId");
        int s = userService.modifyPsw(Integer.parseInt(cookie.getValue()), password);
        mv.setViewName("redirect:/login.html");
        return mv;
    }

    @RequestMapping("/loginOff")
    public ModelAndView loginOffUser(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        Cookie cookie = cookieUtil.findCookie(cookies, "userId");
        int s = userService.deleteUserById(Integer.parseInt(cookie.getValue()));
        mv.setViewName("redirect:/login.html");
        return mv;
    }
}
