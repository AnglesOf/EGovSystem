package com.dm.content.service.impl;

import com.dm.base.utils.myHttpUtil;
import com.dm.content.mapper.MessagesDao;
import com.dm.content.model.po.Message;
import com.dm.content.service.MessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class MessagesServiceImpl implements MessagesService {
    @Autowired
    private MessagesDao messagesDao;

    @Override
    public List<Message> findAllMessages() {
        return messagesDao.selectMessages();
    }

    @Override
    public List<Message> findMessagesLimit(int pageIndex, int pageSize) {
        return messagesDao.selectMessagesLimit(pageIndex, pageSize);
    }

    @Override
    public List<Message> findMessagesLimitWithSec(int pageIndex, int pageSize, String section) {
        return messagesDao.selectMessagesLimitWithSec(pageIndex, pageSize, section);
    }

    @Override
    public List<Message> findUserMessagesByPhone(String phone) {
        return messagesDao.selectUserMessagesByPhone(phone);
    }

    @Override
    public List<Message> findMessagesById(int m_id) {
        return messagesDao.selectMessagesById(m_id);
    }

    @Override
    public List<Message> MoHuFindMessages(String t) {
        return messagesDao.MoSelectMessages(t);
    }

    @Override
    public List<Message> MoHuFindMessagesLimit(int pageIndex, int pageSize, String t) {
        return messagesDao.MoSelectMessagesLimit(pageIndex, pageSize, t);
    }

    @Override
    public int addMessages(Message message) {
        return messagesDao.insertMessages(message);
    }

    @Override
    public int deleteMessages(int m_id) {
        return messagesDao.deleteMessages(m_id);
    }

    @Override
    public int modifyMes(String topic, String context, String area, String bq, int m_id) {
        return messagesDao.updateMes(topic, context, area, bq, m_id);
    }

    @Override
    public int modifyUpDown(int up, int down, int m_id) {
        return messagesDao.updateUpDown(up, down, m_id);
    }

    @Override
    public int selectCounts() {
        return messagesDao.selectCounts();
    }

    @Override
    public int selectAreaCounts(String area) {
        return messagesDao.selectAreaCounts(area);
    }

    @Override
    public int selectBqCounts(String bq) {
        return messagesDao.selectBqCounts(bq);
    }

    @Override
    public List<Message> findUserMessagesLimit(int pageIndex, int pageSize, int u_id) {
        return messagesDao.selectUserMessagesLimit(pageIndex, pageSize, u_id);
    }

    @Override
    public int findMessageStateById(int m_id) {
        return messagesDao.selectMessageStateById(m_id);
    }

    @Override
    public int modifyState(int state, int m_id) {
        return messagesDao.updateState(state, m_id);
    }

    @Override
    public List<Integer> selectDataCount() {
        return messagesDao.selectDataCount();
    }

    @Override
    public String mesClassifier(String text) {
        String result = "未知";
        try {
            result = myHttpUtil.addParams("http://127.0.0.1:8000/textClassifier?text="+text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Message> selectSolveMes() {
        return messagesDao.selectSolveMes();
    }

    @Override
    public List<Message> selectSolvingMes() {
        return messagesDao.selectSolvingMes();
    }

    @Override
    public List<Message> selectSolvedMes() {
        return messagesDao.selectSolvedMes();
    }

    @Override
    public Integer selectSolveMesCount(String bq) {
        return messagesDao.selectSolveMesCount(bq);
    }

    @Override
    public Integer selectSolvingMesCount(String bq) {
        return messagesDao.selectSolvingMesCount(bq);
    }

    @Override
    public Integer selectSolvedMesCount(String bq) {
        return messagesDao.selectSolvedMesCount(bq);
    }

    @Override
    public String selectEarlyTime(String bq) {
        return messagesDao.selectEarlyTime(bq);
    }

    @Override
    public String selectLastTime(String bq) {
        return messagesDao.selectLastTime(bq);
    }

    @Override
    public List<Message> findSecMessagesByPhone(String phone, String section) {
        return messagesDao.selectSecMessagesByPhone(phone, section);
    }

    @Override
    public List<Message> findSecMessagesByMid(String phone, String bq) {
        return messagesDao.selectSecMessagesByMid(phone, bq);
    }

    @Override
    public List<Message> findSolveMesLimitWithSec(int pageIndex, int pageSize, String bq) {
        return messagesDao.selectSolveMesLimitWithSec(pageIndex, pageSize, bq);
    }

    @Override
    public List<Message> findSolvingMesLimitWithSec(int pageIndex, int pageSize, String bq) {
        return messagesDao.selectSolvingMesLimitWithSec(pageIndex, pageSize, bq);
    }

    @Override
    public List<Message> findSolvedMesLimitWithSec(int pageIndex, int pageSize, String bq) {
        return messagesDao.selectSolvedMesLimitWithSec(pageIndex, pageSize, bq);
    }
}
