package com.dm.content.service;


import com.dm.content.model.po.Message;

import java.util.List;

public interface MessagesService {
    List<Message> findAllMessages();
    List<Message> findMessagesLimit(int pageIndex, int pageSize);
    List<Message> findMessagesLimitWithSec(int pageIndex, int pageSize, String section);
    List<Message> findMessagesById(int m_id);
    List<Message> MoHuFindMessages(String t);
    List<Message> MoHuFindMessagesLimit(int pageIndex, int pageSize, String t);
    int findMessageStateById(int m_id);
    int addMessages(Message message);
    int deleteMessages(int m_id);
    int modifyMes(String topic, String context, String area, String bq, int m_id);
    int modifyUpDown(int up, int down, int m_id);
    int modifyState(int state, int m_id);
    int selectCounts();
    int selectAreaCounts(String area);
    int selectBqCounts(String bq);

    List<Message> findUserMessagesLimit(int pageIndex, int pageSize, int u_id);
    List<Message> findUserMessagesByPhone(String phone);

    List<Integer> selectDataCount();

    String mesClassifier(String text);

    List<Message> selectSolveMes();

    List<Message> selectSolvingMes();

    List<Message> selectSolvedMes();


    List<Message> findSecMessagesByPhone(String phone, String section);

    List<Message> findSecMessagesByMid(String phone, String section);

    Integer selectSolveMesCount(String bq);
    Integer selectSolvingMesCount(String bq);
    Integer selectSolvedMesCount(String bq);

    String selectEarlyTime(String bq);
    String selectLastTime(String bq);

    List<Message> findSolveMesLimitWithSec(int pageIndex, int pageSize, String bq);

    List<Message> findSolvingMesLimitWithSec(int pageIndex, int pageSize, String bq);

    List<Message> findSolvedMesLimitWithSec(int pageIndex, int pageSize, String bq);
}
