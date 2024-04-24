package com.dm.content.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@TableName("t_gov_message")
public class Message implements Serializable {
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
    public int state=0; // 0:未办理，1:办理中，2:已解决

    public Message(int u_id, String topic, String context, String area, int up, int down) {
        this.u_id = u_id;
        this.topic = topic;
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.context = context;
        this.area = area;
        this.up = up;
        this.down = down;
    }

    public Message(int m_id, int u_id, String topic, String time, String context, String area, String bq, int up, int down, int state) {
        this.m_id = m_id;
        this.u_id = u_id;
        this.topic = topic;
        this.time = time;
        this.context = context;
        this.area = area;
        this.bq = bq;
        this.up = up;
        this.down = down;
        this.state = state;
    }

    public Message(int u_id, String topic, String context, String area, String bq) {
        this.u_id = u_id;
        this.topic = topic;
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.context = context;
        this.area = area;
        this.bq = bq;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getBq() {
        return bq;
    }

    public void setBq(String bq) {
        this.bq = bq;
    }


    @Override
    public String toString() {
        return "Message{" +
                "m_id=" + m_id +
                ", u_id=" + u_id +
                ", topic='" + topic + '\'' +
                ", time='" + time + '\'' +
                ", context='" + context + '\'' +
                ", area='" + area + '\'' +
                ", bq='" + bq + '\'' +
                ", up=" + up +
                ", down=" + down +
                ", state=" + state +
                '}';
    }
}
