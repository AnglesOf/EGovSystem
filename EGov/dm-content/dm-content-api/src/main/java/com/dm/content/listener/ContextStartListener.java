package com.dm.content.listener;

import com.dm.content.service.HotTopicService;
import com.dm.content.service.MessagesService;
import com.dm.content.service.RedisService;
import com.dm.content.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//@WebListener(不能用，不能触发事件)
@Component
public class ContextStartListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    public MessagesService messagesService;

    @Autowired
    public HotTopicService hotTopicService;

    @Autowired
    public UserService userService;

    @Autowired
    public RedisService redisService;

    public void setRedisThread(int colckTime) {
        new Thread(() -> {
            while (true) {
                System.out.println("---------------------------Redis开始构建数据库---------------------------");
                System.out.println("-----------------------------开始查询全局数据-----------------------------");
                redisService.set("visitCount", "1");
                redisService.set("messageCount", messagesService.selectCounts() + "");
                redisService.set("userCount", userService.selectUserCounts() + "");
                System.out.println("-----------------------------开始查询区域数据-----------------------------");
                redisService.set("AMessageCount", messagesService.selectAreaCounts("A") + "");
                redisService.set("BMessageCount", messagesService.selectAreaCounts("B") + "");
                redisService.set("CMessageCount", messagesService.selectAreaCounts("C") + "");
                redisService.set("DMessageCount", messagesService.selectAreaCounts("D") + "");
                System.out.println("-----------------------------开始查询标签数据-----------------------------");
                redisService.set("cxjsMessageCount", messagesService.selectBqCounts("城乡建设") + "");
                redisService.set("dzdwMessageCount", messagesService.selectBqCounts("党务政务") + "");
                redisService.set("gtzyMessageCount", messagesService.selectBqCounts("国土资源") + "");
                redisService.set("hjbhMessageCount", messagesService.selectBqCounts("环境保护") + "");
                redisService.set("jjjcMessageCount", messagesService.selectBqCounts("纪检监察") + "");
                redisService.set("jtysMessageCount", messagesService.selectBqCounts("交通运输") + "");
                redisService.set("jywtMessageCount", messagesService.selectBqCounts("教育文体") + "");
                redisService.set("jjglMessageCount", messagesService.selectBqCounts("经济管理") + "");
                redisService.set("kjxxMessageCount", messagesService.selectBqCounts("科技与信息产业") + "");
                redisService.set("ndshMessageCount", messagesService.selectBqCounts("劳动和社会保障") + "");
                redisService.set("mzMessageCount", messagesService.selectBqCounts("民政") + "");
                redisService.set("nyncMessageCount", messagesService.selectBqCounts("农村农业") + "");
                redisService.set("smlyMessageCount", messagesService.selectBqCounts("商贸旅游") + "");
                redisService.set("wsjsMessageCount", messagesService.selectBqCounts("卫生计生") + "");
                redisService.set("zfMessageCount", messagesService.selectBqCounts("政法") + "");
                redisService.set("wzMessageCount", messagesService.selectBqCounts("未知") + "");
                System.out.println("------------------------------开始查询日期列表------------------------------");
                redisService.setInt("dataCount", messagesService.selectDataCount());
                System.out.println("---------------------------开始查询未办理留言列表-----------------------------");
                redisService.setMessage("solveMes", messagesService.selectSolveMes());
                System.out.println("--------------------------开始查询正在办理留言列表-----------------------------");
                redisService.setMessage("solvingMes", messagesService.selectSolvingMes());
                System.out.println("-----------------------------开始查询已办理留言列表----------------------------");
                redisService.setMessage("solvedMes", messagesService.selectSolvedMes());
                System.out.println("-----------------------------开始查询热点留言列表-----------------------------");
                redisService.setHotTopic("hotTopic", hotTopicService.findAllHotTopic());
                System.out.println("-----------------------------Redis数据库构建完成-----------------------------");
                try {
                    Thread.sleep(colckTime);
//                    Thread.sleep(1000 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "diyFirstThread").start();
    }

    public void calcHotTopicThread(int colckTime) {
        new Thread(() -> {
            while (true) {
                System.out.println("计算热点");
                hotTopicService.calcHotTopic();
                try {
                    Thread.sleep(colckTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "diySecThread").start();
    }

    public void calcCYThread(int colckTime) {
        new Thread(() -> {
            while (true) {
                System.out.println("计算词云");
                hotTopicService.calCiYun();
                try {
                    Thread.sleep(colckTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "diyThiThread").start();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        setRedisThread(60 * 60 * 1000);
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) { //保证只执行一次
            // 每1小时（1000毫秒*60秒*60分钟）更新数据库数据到缓存
            setRedisThread(60 * 60 * 1000);
            // 每7天（1000毫秒*60秒*60分钟*24小时*7天）计算一次热点信息
//            calcHotTopicThread(7 * 24 * 60 * 60 * 1000);
            // 每7天（1000毫秒*60秒*60分钟*24小时*7天）计算一次词云信息
//            calcCYThread(7 * 24 * 60 * 60 * 1000);
        }
    }


}
