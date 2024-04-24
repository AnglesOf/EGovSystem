package com.dm.content.service.impl;

import com.dm.content.mapper.MessagesDao;
import com.dm.content.mapper.SectionDao;
import com.dm.content.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
@Slf4j
@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private MessagesDao messagesDao;

    @Override
    public String findFunc(String section) {
        return sectionDao.selctFunc(section);
    }

    @Override
    public HashMap<String, String> JiXiao(String section) {
        String bq = sectionDao.selctFunc(section);
        HashMap<String, String> map = new HashMap<String, String>();
        int secMesCount = messagesDao.selectBqCounts(bq);
        int solveMesCount = messagesDao.selectSolveMesCount(bq);
        int solvingMesCount = messagesDao.selectSolvingMesCount(bq);
        int solvedMesCount = messagesDao.selectSolvedMesCount(bq);
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        double diffTime=0;
        try {
            Date date = fmt.parse(messagesDao.selectEarlyTime(bq));
            Date date2 = fmt.parse(messagesDao.selectLastTime(bq));
            diffTime = (date2.getTime() - date.getTime())/(60.0*60.0*1000.0*24.0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        map.put("secMesCount", String.valueOf(secMesCount));
        map.put("solveMesCount", String.valueOf(solveMesCount));
        map.put("solvingMesCount", String.valueOf(solvingMesCount));
        map.put("solvedMesCount", String.valueOf(solvedMesCount));
        map.put("diffTime", String.format("%.2f", diffTime));
        return map;
    }
}
