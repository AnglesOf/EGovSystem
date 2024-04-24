package com.dm.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.content.model.po.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessagesDao extends BaseMapper<Message> {
    List<Message> selectMessages();
    List<Message> selectMessagesLimit(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
    List<Message> selectUserMessagesLimit(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("u_id") int u_id);
    List<Message> selectUserMessagesByPhone(String phone);
    List<Message> selectMessagesById(int m_id);
    List<Message> MoSelectMessages(String t);
    List<Message> MoSelectMessagesLimit(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize,  @Param("t") String t);
    int selectMessageStateById(int m_id);
    int insertMessages(Message message);
    int deleteMessages(int m_id);
    int updateMes(@Param("topic") String topic, @Param("context") String context, @Param("area") String area, @Param("bq") String bq, @Param("m_id") int m_id);
    int updateUpDown(@Param("up") int up, @Param("down") int down, @Param("m_id") int m_id);
    int updateState(@Param("state") int state, @Param("m_id") int m_id);
    int selectCounts();
    int selectAreaCounts(String area);
    int selectBqCounts(String bq);
    List<Message> selectSolveMes();
    List<Message> selectSolvingMes();
    List<Message> selectSolvedMes();

    List<Integer> selectDataCount();
    Integer selectSolveMesCount(String bq);
    Integer selectSolvingMesCount(String bq);
    Integer selectSolvedMesCount(String bq);

    String selectEarlyTime(String bq);
    String selectLastTime(String bq);

    List<Message> selectSecMessagesByPhone(@Param("phone") String phone, @Param("section") String section);

    List<Message> selectSecMessagesByMid(@Param("m_id") String m_id, @Param("section") String section);

    List<Message> selectMessagesLimitWithSec(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("bq") String bq);

    List<Message> selectSolveMesLimitWithSec(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("bq") String bq);
    List<Message> selectSolvingMesLimitWithSec(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("bq") String bq);
    List<Message> selectSolvedMesLimitWithSec(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("bq") String bq);


}
