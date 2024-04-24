package com.dm.content.service;


import com.dm.content.model.po.SectionUser;

public interface SecUserService {
    int checkLogin(String phone, String password);
    SectionUser findUserByPhone(String phone);
}
