package com.dm.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.content.model.po.HotTopic;

import java.util.List;

public interface HotTopicDao extends BaseMapper<HotTopic> {
    List<HotTopic> selectHotTopic();
    int insertHotTopic(HotTopic hotTopic);
    int deleteHotTopic(int m_id);
}
