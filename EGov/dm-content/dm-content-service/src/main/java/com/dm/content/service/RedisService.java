package com.dm.content.service;


import com.dm.content.model.po.HotTopic;
import com.dm.content.model.po.Message;

import java.util.List;

public interface RedisService {
    boolean set(final String key, String value);
    boolean setInt(final String key, List<Integer> value);
    boolean setHotTopic(final String key, List<HotTopic> value);
    boolean setMessage(final String key, List<Message> value);
    String get(final String key);
    List<Integer> getListInteger(final String key);
    List<HotTopic> getListHotTopic(final String key);
    List<Message> getListMessage(final String key);
    void flushAll();
}
