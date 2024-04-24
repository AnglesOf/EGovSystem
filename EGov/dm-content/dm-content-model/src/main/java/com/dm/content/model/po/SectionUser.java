package com.dm.content.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("t_section_user")
public class SectionUser implements Serializable {
    private static final long serialVersionUID = 1L;
    String id;
    String name;
    String phone;
    String password;
    String sex=null;
    String section;

    public SectionUser() {}

    public SectionUser(String id, String name, String phone, String password, String section) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.section = section;
    }

    public SectionUser(String id, String name, String phone, String password, String sex, String section) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.sex = sex;
        this.section = section;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
