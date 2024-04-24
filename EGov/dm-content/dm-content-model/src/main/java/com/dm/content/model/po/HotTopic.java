package com.dm.content.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@TableName("t_hot_topic")
public class HotTopic implements Serializable {
    private static final long serialVersionUID = 1L;
    public int m_id;
    public int u_id;
    public String topic;
    public String time;
    public String context;
    public String area;
    public String bq="未知";
    public int up=0;
    public int down=0;
    public int hot =0; // 0:未办理，1:办理中，2:已解决

    public HotTopic(int u_id, String topic, String context, String area, int up, int down) {
        this.u_id = u_id;
        this.topic = topic;
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.context = context;
        this.area = area;
        this.up = up;
        this.down = down;
    }

    public HotTopic(int m_id, int u_id, String topic, String time, String context, String area, String bq, int up, int down, int hot) {
        this.m_id = m_id;
        this.u_id = u_id;
        this.topic = topic;
        this.time = time;
        this.context = context;
        this.area = area;
        this.bq = bq;
        this.up = up;
        this.down = down;
        this.hot = hot;
    }

    public HotTopic(int u_id, String topic, String context, String area) {
        this.u_id = u_id;
        this.topic = topic;
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.context = context;
        this.area = area;
    }

    @Override
    public String toString() {
        return "HotTopic{" +
                "m_id=" + m_id +
                ", u_id=" + u_id +
                ", topic='" + topic + '\'' +
                ", time='" + time + '\'' +
                ", context='" + context + '\'' +
                ", area='" + area + '\'' +
                ", bq='" + bq + '\'' +
                ", up=" + up +
                ", down=" + down +
                ", hot=" + hot +
                '}';
    }
}
