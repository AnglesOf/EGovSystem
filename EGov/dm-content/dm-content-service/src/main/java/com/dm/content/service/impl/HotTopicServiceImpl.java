package com.dm.content.service.impl;

import com.dm.base.utils.myHttpUtil;
import com.dm.content.mapper.HotTopicDao;
import com.dm.content.model.po.HotTopic;
import com.dm.content.service.HotTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Slf4j
@Service
public class HotTopicServiceImpl implements HotTopicService {
    @Autowired
    private HotTopicDao hotTopicDao;

    @Override
    public List<HotTopic> findAllHotTopic() {
        return hotTopicDao.selectHotTopic();
    }

    @Override
    public int addHotTopic(HotTopic hotTopic) {
        return hotTopicDao.insertHotTopic(hotTopic);
    }

    @Override
    public int deleteHotTopic(int m_id) {
        return hotTopicDao.deleteHotTopic(m_id);
    }

    @Override
    public int calcHotTopic() {
        String result="0";
        try {
            result = myHttpUtil.addParams("http://127.0.0.1:8000/calcHotTopic?flag=1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(result);
    }

    @Override
    public int calCiYun() {
        String result="0";
        try {
            result = myHttpUtil.addParams("http://127.0.0.1:8000/calCY");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(result);
    }
}
