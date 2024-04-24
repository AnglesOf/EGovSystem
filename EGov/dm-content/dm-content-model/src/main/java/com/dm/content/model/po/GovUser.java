package com.dm.content.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_gov_user")
public class GovUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id=null;
    public String name;
    public String phone;
    private String password;
    private int flag=1;
    public String sex;

    public GovUser(Integer id, String name, String phone, String password, int flag, String sex) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.flag = flag;
        this.sex = sex;
    }

    public GovUser() {}

    public GovUser(Integer id, String name, String phone, String password, String sex) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.sex = sex;
    }
}
