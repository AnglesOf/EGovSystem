package com.dm.content.service.impl;

import com.dm.content.mapper.SectionUserDao;
import com.dm.content.model.po.SectionUser;
import com.dm.content.service.SecUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class SecUserServiceImpl implements SecUserService {
    @Autowired
    private SectionUserDao sectionUserDao;

    @Override
    public int checkLogin(String phone, String password) {
        SectionUser sectionUser = sectionUserDao.selectUserByPhone(phone);
        // 0表示账号不存在，1表示查询没问题，2表示密码错误
        int loginState = 0;
        if (sectionUser != null) {
            String psw = sectionUser.getPassword();
            loginState = psw.equals(password) ? 1 : 2;
        }
        return loginState;
    }

    @Override
    public SectionUser findUserByPhone(String phone) {
        return sectionUserDao.selectUserByPhone(phone);
    }
}
