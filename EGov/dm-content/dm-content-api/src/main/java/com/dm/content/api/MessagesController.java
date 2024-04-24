package com.dm.content.api;

import com.dm.base.utils.cookieUtil;
import com.dm.content.model.po.Message;
import com.dm.content.service.MessagesService;
import com.dm.content.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    public MessagesService messagesService;

    @Autowired
    public SectionService sectionService;

    @RequestMapping("/findMessages")
    public ModelAndView findMessages(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/userHTML/ListMessages.html");
        return mv;
    }

    @RequestMapping("/findUserMessages")
    public ModelAndView findUserMessages(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/userHTML/userMessages.html");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/findUserMessagesByPhone")
    public List<Message> findUserMessagesByPhone(String phone){
        return messagesService.findUserMessagesByPhone(phone);
    }

    @ResponseBody
    @RequestMapping("/searchSecMessagesByPhone")
    public List<Message> searchSecMessagesByPhone(String phone, String section){
        return messagesService.findSecMessagesByPhone(phone, section);
    }

    @ResponseBody
    @RequestMapping("/searchSecMessagesByMid")
    public List<Message> searchSecMessagesByMid(String phone, String section){
        return messagesService.findSecMessagesByMid(phone, section);
    }

    @ResponseBody
    @RequestMapping("/findMessagesLimit")
    private List<Message> findMessagesLimit(int pageIndex, int pageSize){
        return messagesService.findMessagesLimit(pageIndex, pageSize);
    }

    @RequestMapping("findMessageStateById")
    public int findMessageStateById(int m_id){
        return messagesService.findMessageStateById(m_id);
    }

    @ResponseBody
    @RequestMapping("/findUserMessagesLimit")
    private List<Message> findUserMessagesLimit(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Cookie cookie = cookieUtil.findCookie(cookies, "userId");
        return messagesService.findUserMessagesLimit(pageIndex, pageSize, Integer.parseInt(cookie.getValue()));
    }

    @ResponseBody
    @RequestMapping("/findMessagesLimitWithSec")
    private List<Message> findMessagesLimitWithSec(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize,  @RequestParam("section") String section){
        return messagesService.findMessagesLimitWithSec(pageIndex,pageSize,sectionService.findFunc(section));
    }
    @ResponseBody
    @RequestMapping("/findSolveMesLimitWithSec")
    private List<Message> findSolveMesLimitWithSec(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize,  @RequestParam("section") String section){
        return messagesService.findSolveMesLimitWithSec(pageIndex,pageSize,sectionService.findFunc(section));
    }
    @ResponseBody
    @RequestMapping("/findSolvingMesLimitWithSec")
    private List<Message> findSolvingMesLimitWithSec(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize,  @RequestParam("section") String section){
        return messagesService.findSolvingMesLimitWithSec(pageIndex,pageSize,sectionService.findFunc(section));
    }
    @ResponseBody
    @RequestMapping("/findSolvedMesLimitWithSec")
    private List<Message> findSolvedMesLimitWithSec(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize,  @RequestParam("section") String section){
        return messagesService.findSolvedMesLimitWithSec(pageIndex,pageSize,sectionService.findFunc(section));
    }

    @RequestMapping("/searchByMid")
    private ModelAndView findMessagesById(int m_id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("messagesList", messagesService.findMessagesById(m_id));
        mv.setViewName("forward:/WEB-INF/views/modifyMessage.jsp");
        return mv;
    }

    @RequestMapping("/searchByMidForGov")
    private ModelAndView findMessagesByIdForGov(int m_id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("messagesList", messagesService.findMessagesById(m_id));
        mv.setViewName("forward:/WEB-INF/views/modifyMessageForGov.jsp");
        return mv;
    }

    @RequestMapping("/MoHuSearch")
    private ModelAndView MoHuSearch(String t){
        ModelAndView mv = new ModelAndView();
        mv.addObject("t", t);
        mv.setViewName("forward:/WEB-INF/views/MoHuSearchResult.jsp");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/MoHuSearchLimit")
    private List<Message> MoHuSearchLimit(int pageIndex, int pageSize, String t){
        return messagesService.MoHuFindMessagesLimit(pageIndex, pageSize, t);
    }

    @ResponseBody
    @RequestMapping("/searchByMidAjax")
    private List<Message> findMessagesByIdAjax(int m_id){
        return messagesService.findMessagesById(m_id);
    }

    @RequestMapping("/visitMessage")
    private ModelAndView visitMessage(int m_id){
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("messagesList", messagesService.findMessagesById(m_id));
//        mv.setViewName("forward:/views/visitMessage.jsp");
        ModelAndView mv = new ModelAndView("visitMessage");
        mv.addObject("messagesList", messagesService.findMessagesById(m_id));
//        mv.setViewName("forward:/views/visitMessage.jsp");
        return mv;
    }

    @RequestMapping("/addMessages")
    public ModelAndView addMessages(@RequestParam("topic") String topic, @RequestParam("context") String context, @RequestParam("area") String area, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        Cookie cookie = cookieUtil.findCookie(cookies, "userId");
        int u_id = Integer.parseInt(cookie.getValue());
        String bq = messagesService.mesClassifier(context);
        messagesService.addMessages(new Message(u_id, topic, context, area, bq));
        mv.setViewName("redirect:/userHTML/userMessages.html");
        return mv;
    }

    @RequestMapping("/modifyMes")
    private ModelAndView modifyMes(int m_id, String topic, String context, String area, String bq){
        messagesService.modifyMes(topic, context, area, bq, m_id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/userHTML/userMessages.html");
        return mv;
    }

    @RequestMapping("/modifyMesForGov")
    public ModelAndView modifyMesForGov(int m_id, String topic, String context, String area, String bq){
        messagesService.modifyMes(topic, context, area, bq, m_id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/govUserHTML/ListUserMessages.html");
        return mv;
    }

    @RequestMapping("/modifyUpDown")
    public void modifyUpDown(int up, int down, int m_id){
        messagesService.modifyUpDown(up, down, m_id);
    }

    @ResponseBody
    @RequestMapping("/modifyState")
    public int modifyState(int state, int m_id){
        int r = messagesService.modifyState(state, m_id);
        return r!=0?state:state-1;
    }

    @RequestMapping("/removeMessages")
    public ModelAndView removeMessages(int m_id){
        ModelAndView mv = new ModelAndView();
        messagesService.deleteMessages(m_id);
        mv.setViewName("redirect:/userHTML/userMessages.html");
        return mv;
    }

    @RequestMapping("/removeMessagesForGov")
    public ModelAndView removeMessagesForGov(int m_id){
        ModelAndView mv = new ModelAndView();
        messagesService.deleteMessages(m_id);
        mv.setViewName("redirect:/govUserHTML/ListUserMessages.html");
        return mv;
    }

    @ResponseBody
    @RequestMapping("mesClassifier")
    private String mesClassifier(String context){
        return messagesService.mesClassifier(context);
    }


}
