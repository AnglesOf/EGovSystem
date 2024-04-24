package com.dm.content.service.impl;

import com.dm.content.model.po.HotTopic;
import com.dm.content.model.po.Message;
import com.dm.content.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 写入缓存
     *
     * @param key   键
     * @param value 值
     * @return isSuccessful
     */

    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean setInt(final String key, List<Integer> value) {
        boolean result = false;
        ArrayList<String> value1 = new ArrayList<>();
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            for (Integer i : value) {
                value1.add(Integer.toString(i));
            }
            listOperations.rightPushAll(key, value1);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean setHotTopic(String key, List<HotTopic> value) {
        boolean result = false;
        ArrayList<String> arrayList = new ArrayList<>();
        for (HotTopic h : value) {
            arrayList.add(h.toString());
        }
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            listOperations.rightPushAll(key, arrayList);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean setMessage(String key, List<Message> value) {
        boolean result = false;
        ArrayList<String> arrayList = new ArrayList<>();
        for (Message h : value) {
            arrayList.add(h.toString());
        }
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            listOperations.rightPushAll(key, arrayList);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key 键
     * @return result
     */
    public String get(final String key) {
        String result = null;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            result = (String) operations.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Integer> getListInteger(String key) {
        List<Integer> result = new ArrayList<>();
        List<String> result_options = new ArrayList<>();
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            result_options = (List<String>) listOperations.range(key, 0, -1);
            for (String s : result_options) {
                if (s.equals(null)) {
                    result.add(0);
                } else {
                    result.add(Integer.parseInt(s));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void flushAll() {
        try {
            Set<String> keys = redisTemplate.keys("*");
            redisTemplate.delete(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public List<HotTopic> getListHotTopic(String key) {
        List<HotTopic> result = new ArrayList<>();
        List<String> result_options = new ArrayList<>();
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            result_options = (List<String>) listOperations.range(key, 0, -1);
            for (String string : result_options) {
                Map<String, String> properties = new HashMap<>();
                Pattern pattern = Pattern.compile("(\\w+)=([^,}]+)");
                Matcher matcher = pattern.matcher(string);
                while (matcher.find()) {
                    String propertyName = matcher.group(1);
                    String propertyValue = matcher.group(2);
                    properties.put(propertyName, propertyValue);
                }
                result.add(new HotTopic(Integer.parseInt(properties.get("m_id")), Integer.parseInt(properties.get("u_id")), properties.get("topic"), properties.get("time"), properties.get("context"), properties.get("area"), properties.get("bq"), Integer.parseInt(properties.get("up")), Integer.parseInt(properties.get("down")), Integer.parseInt(properties.get("hot"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Message> getListMessage(String key) {
        List<Message> result = new ArrayList<>();
        List<String> result_options = new ArrayList<>();
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            result_options = (List<String>) listOperations.range(key, 0, -1);
            for (String string : result_options) {
                Map<String, String> properties = new HashMap<>();
                Pattern pattern = Pattern.compile("(\\w+)=([^,}]+)");
                Matcher matcher = pattern.matcher(string);
                while (matcher.find()) {
                    String propertyName = matcher.group(1);
                    String propertyValue = matcher.group(2);
                    properties.put(propertyName, propertyValue);
                }
                result.add(new Message(Integer.parseInt(properties.get("m_id")), Integer.parseInt(properties.get("u_id")), properties.get("topic"), properties.get("time"), properties.get("context"), properties.get("area"), properties.get("bq"), Integer.parseInt(properties.get("up")), Integer.parseInt(properties.get("down")), Integer.parseInt(properties.get("state"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
