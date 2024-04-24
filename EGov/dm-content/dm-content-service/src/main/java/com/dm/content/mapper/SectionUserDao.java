package com.dm.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.content.model.po.SectionUser;

public interface SectionUserDao extends BaseMapper<SectionUser> {
    SectionUser selectUserByPhone(String phone);
}
