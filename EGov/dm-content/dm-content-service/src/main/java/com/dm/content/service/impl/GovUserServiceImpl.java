package com.dm.content.service.impl;

import com.dm.content.mapper.GovUserDao;
import com.dm.content.model.po.GovUser;
import com.dm.content.service.GovUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GovUserServiceImpl implements GovUserService {
    @Autowired
    private GovUserDao govUserDao;

    @Override
    public List<GovUser> findGovUser() {
        return govUserDao.selectGovUser();
    }

    @Override
    public GovUser findGovUserById(int id) {
        return govUserDao.selectGovUserById(id);
    }

    @Override
    public GovUser findGovUserByPhone(String phone) {
        return govUserDao.selectGovUserByPhone(phone);
    }

    @Override
    public int addGovUser(GovUser govUser) {
        return govUserDao.insertGovUser(govUser);
    }

    @Override
    public int deleteGovUserById(int id) {
        return govUserDao.deleteGovUserById(id);
    }

    @Override
    public int deleteGovUserByPhone(String phone) {
        return govUserDao.deleteGovUserByPhone(phone);
    }

    @Override
    public int modifyInfo(int id, String name, String phone, String sex) {
        return govUserDao.updateInfo(id, name, phone, sex);
    }

    @Override
    public int modifyPsw(int id, String password) {
        return govUserDao.updatePsw(id, password);
    }

    @Override
    public int selectGovUserCounts() {
        return govUserDao.selectGovUserCounts();
    }

    @Override
    public int checkLogin(String phone, String password) {
        GovUser govUser = findGovUserByPhone(phone);
//        System.out.println(user);
        // 0表示账号不存在，1表示查询没问题，2表示密码错误.
        int loginState = 0;
        if (govUser != null) {
            String psw = govUser.getPassword();
            loginState = psw.equals(password) ? 1 : 2;
        }
        return loginState;
    }
}
