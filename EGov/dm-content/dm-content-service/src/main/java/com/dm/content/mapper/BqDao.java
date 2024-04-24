package com.dm.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.content.model.po.Bq;

import java.util.List;

public interface BqDao extends BaseMapper<Bq> {
    List<Bq> selectBq();
}
