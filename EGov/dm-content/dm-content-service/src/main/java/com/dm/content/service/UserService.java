package com.dm.content.service;



import com.dm.content.model.po.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
    List<User> findUserLimit(int pageIndex, int pageSize);
    User findUserById(int id);
    User findUserByPhone(String phone);
    int findUserState(int id);

    int addUser(User user);

    int deleteUserById(int id);
    int deleteUserByPhone(String phone);

    int modifyInfo(int id, String name, String phone, String sex);
    int modifyPsw(int id, String password);

    int closeUserById(int id);
    int closeUserByPhone(String phone);

    int openUserById(int id);

    int selectUserCounts();

    int checkLogin(String phone, String password);
}
