package com.dm.content.service.impl;

import com.dm.content.mapper.UserDao;
import com.dm.content.model.po.User;
import com.dm.content.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAllUser() {
        return userDao.selectUser();
    }

    @Override
    public List<User> findUserLimit(int pageIndex, int pageSize) {
        return userDao.selectUserLimit(pageIndex, pageSize);
    }

    @Override
    public User findUserById(int id) {
        return userDao.selectUserById(id);
    }

    @Override
    public User findUserByPhone(String phone) {
        return userDao.selectUserByPhone(phone);
    }

    @Override
    public int findUserState(int id) {
        return userDao.selectUserState(id);
    }

    @Override
    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int deleteUserByPhone(String phone) {
        return userDao.deleteUserByPhone(phone);
    }

    @Override
    public int modifyInfo(int id, String name, String phone, String sex) {
        return userDao.updateInfo(id, name, phone, sex);
    }

    @Override
    public int modifyPsw(int id, String password) {
        return userDao.updatePsw(id, password);
    }

    @Override
    public int closeUserById(int id) {
        return userDao.closeUserById(id);
    }

    @Override
    public int closeUserByPhone(String phone) {
        return userDao.closeUserByPhone(phone);
    }


    @Override
    public int openUserById(int id) {
        return userDao.openUserById(id);
    }

    @Override
    public int selectUserCounts() {
        return userDao.selectUserCounts();
    }

    @Override
    public int checkLogin(String phone, String password) {
        User user = findUserByPhone(phone);
//        System.out.println(user);
        // 0表示账号不存在，1表示查询没问题，2表示密码错误，3表示用户被封禁
        int loginState = 0;
        if (user != null) {
            String psw = user.getPassword();
            int state = user.getState();
            loginState = psw.equals(password) ? 1 : state==1 ? 3 : 2;
        }
        return loginState;
    }
}
