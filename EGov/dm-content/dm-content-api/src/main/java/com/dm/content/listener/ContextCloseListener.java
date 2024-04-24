package com.dm.content.listener;

import com.dm.content.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextCloseListener implements ApplicationListener<ContextClosedEvent> {
    @Autowired
    public RedisService redisService;

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        redisService.flushAll();
        // 关闭线程
        Thread t = getThreadByName("diyFirstThread");
        if (t != null) {
            if (t.isInterrupted())
                t.interrupt();
            else
                t.stop();
        }
        t = getThreadByName("diySecThread");
        if (t != null) {
            if (t.isInterrupted())
                t.interrupt();
            else
                t.stop();
        }
        t = getThreadByName("diyThiThread");
        if (t != null) {
            if (t.isInterrupted())
                t.interrupt();
            else
                t.stop();
        }
    }

    /**
     * 根据线程名称获取线程
     * @param threadName 线程名称
     * @return 线程
     */
    public Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) {
                System.out.println(t.getName());
                return t;
            }
        }
        return null;
    }
}
