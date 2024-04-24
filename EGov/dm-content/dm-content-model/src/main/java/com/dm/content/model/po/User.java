package com.dm.content.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
    private static final long serialVersionUID = 1L;
    private Integer id=null;
    public String name;
    public String phone;
    private String password;
    private int flag=0;
    public String sex;
    public int state=0;

    public User() {
    }

    public User(String name, String phone, String password, String sex) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.sex = sex;
    }

    public User(Integer id, String name, String phone, String password, int flag, String sex, int state) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.flag = flag;
        this.sex = sex;
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", flag=" + flag +
                ", sex='" + sex + '\'' +
                ", state=" + state +
                '}';
    }
}
