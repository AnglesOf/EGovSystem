package com.dm.base.utils;

import org.springframework.web.servlet.ModelAndView;

public class alterResult {
    /**
     *弹出
     * @param msg 失败显示的信息
     * @param url 成功后跳转的地址
     * @param mv ModelAndView对象
     */
    public static void tanChuang(int num, String msg, String url, ModelAndView mv){
        if (num!=0) {
            mv.addObject("msg", "成功" + msg);
        }else {
            mv.addObject("msg", "失败" + msg);
        }
        mv.addObject("loc", url);
        mv.setViewName("forward:/WEB-INF/views/tanChuang.jsp");
    }

    public static void tanChuang(String msg, String url, ModelAndView mv){
        mv.addObject("msg", msg);
        mv.addObject("loc", url);
        mv.setViewName("forward:/WEB-INF/views/tanChuang.jsp");
    }
}
