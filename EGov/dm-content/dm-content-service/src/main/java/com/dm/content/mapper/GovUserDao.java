package com.dm.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.content.model.po.GovUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GovUserDao extends BaseMapper<GovUser> {
    List<GovUser> selectGovUser();
    GovUser selectGovUserById(int id);
    GovUser selectGovUserByPhone(String phone);

    int insertGovUser(GovUser user);

    int deleteGovUserById(int id);
    int deleteGovUserByPhone(String phone);

    int updateInfo(@Param("id") int id, @Param("name") String name, @Param("phone") String phone, @Param("sex") String sex);
    int updatePsw(int id, String password);

    int selectGovUserCounts();
}
