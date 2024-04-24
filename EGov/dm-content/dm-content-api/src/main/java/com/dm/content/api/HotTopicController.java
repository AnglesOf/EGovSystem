package com.dm.content.api;


import com.dm.content.model.po.HotTopic;
import com.dm.content.service.HotTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hotTopic")
public class HotTopicController {
    @Autowired
    public HotTopicService hotTopicService;

    @ResponseBody
    @RequestMapping("/findHotTopicAjax")
    public List<HotTopic> findHotTopicAjax(){
        return hotTopicService.findAllHotTopic();
    }

    @ResponseBody
    @RequestMapping("/findHotTopic")
    public List<HotTopic> findHotTopic(){
        return hotTopicService.findAllHotTopic();
    }

    @RequestMapping("/addHotTopic")
    public int addHotTopic(){
        return 0;
    }

    @RequestMapping("/removeHotTopic")
    public int removeHotTopic(int m_id){
        return hotTopicService.deleteHotTopic(m_id);
    }

    @ResponseBody
    @RequestMapping("/calcHotTopic")
    public int calcHotTopic(){
        return hotTopicService.calcHotTopic();
    }

    @ResponseBody
    @RequestMapping("/calCiYun")
    public int calCiYun(){
        return hotTopicService.calCiYun();
    }
}
