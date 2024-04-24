package com.dm.content.service.impl;

import com.dm.content.mapper.BqDao;
import com.dm.content.model.po.Bq;
import com.dm.content.service.BqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class BqServiceImpl  implements BqService {
    @Autowired
    private BqDao bqDao;

    @Override
    public List<Bq> findAllBq() {
        return bqDao.selectBq();
    }
}
