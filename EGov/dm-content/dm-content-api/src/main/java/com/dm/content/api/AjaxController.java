package com.dm.content.api;

import com.dm.content.model.po.Cache;
import com.dm.content.service.RedisService;
import com.dm.content.service.SectionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "异步请求接口",tags = "异步请求接口")
@RestController
@RequestMapping("/redisDate")
public class AjaxController {
    @Autowired
    public RedisService redisService;

    @Autowired
    public SectionService sectionService;

    @RequestMapping("/getCache")
    public Cache getCache(){
//        System.out.println(redisService.get("visitCount") + "" + redisService.get("ordersCount") + redisService.get("serversCount"));
        return new Cache(redisService.get("visitCount"), redisService.get("messageCount"), redisService.get("userCount"),
                redisService.get("AMessageCount"), redisService.get("BMessageCount"), redisService.get("CMessageCount"),
                redisService.get("DMessageCount"), redisService.get("cxjsMessageCount"), redisService.get("dzdwMessageCount"),
                redisService.get("gtzyMessageCount"), redisService.get("hjbhMessageCount"), redisService.get("jjjcMessageCount"),
                redisService.get("jtysMessageCount"), redisService.get("jywtMessageCount"), redisService.get("jjglMessageCount"),
                redisService.get("kjxxMessageCount"), redisService.get("ndshMessageCount"), redisService.get("mzMessageCount"),
                redisService.get("nyncMessageCount"), redisService.get("smlyMessageCount"), redisService.get("wsjsMessageCount"),
                redisService.get("zfMessageCount"), redisService.get("wzMessageCount"), redisService.getListInteger("dataCount"));
    }

    @RequestMapping("/getIndex")
    public Cache getIndex(){
        return new Cache(redisService.getListMessage("solveMes"), redisService.getListMessage("solvingMes"),
                redisService.getListMessage("solvedMes"), redisService.getListHotTopic("hotTopic"));
    }

    @RequestMapping("getSecJiXiao")
    public Map<String, String> getSecJiXiao(String section){
        return sectionService.JiXiao(section);
    }


}
