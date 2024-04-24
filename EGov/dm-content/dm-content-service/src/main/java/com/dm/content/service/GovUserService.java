package com.dm.content.service;


import com.dm.content.model.po.GovUser;

import java.util.List;

public interface GovUserService {
    List<GovUser> findGovUser();
    GovUser findGovUserById(int id);
    GovUser findGovUserByPhone(String phone);

    int addGovUser(GovUser govUser);

    int deleteGovUserById(int id);
    int deleteGovUserByPhone(String phone);

    int modifyInfo(int id, String name, String phone, String sex);
    int modifyPsw(int id, String password);

    int selectGovUserCounts();

    int checkLogin(String phone, String password);
}
