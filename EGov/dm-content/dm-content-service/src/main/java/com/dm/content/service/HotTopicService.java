package com.dm.content.service;


import com.dm.content.model.po.HotTopic;

import java.util.List;


public interface HotTopicService {
    List<HotTopic> findAllHotTopic();
    int addHotTopic(HotTopic hotTopic);
    int deleteHotTopic(int m_id);
    int calcHotTopic();
    int calCiYun();
}
