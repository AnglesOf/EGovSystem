package com.dm.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.content.model.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends BaseMapper<User> {
    List<User> selectUser();
    List<User> selectUserLimit(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
    User selectUserById(int id);
    User selectUserByPhone(String phone);
    int selectUserState(int id);

    int insertUser(User user);

    int deleteUserById(int id);
    int deleteUserByPhone(String phone);

    int updateInfo(@Param("id") int id, @Param("name") String name, @Param("phone") String phone, @Param("sex") String sex);
    int updatePsw(@Param("id") int id, @Param("password") String password);

    int closeUserById(int id);
    int closeUserByPhone(String phone);

    int openUserById(int id);

    int selectUserCounts();
}
