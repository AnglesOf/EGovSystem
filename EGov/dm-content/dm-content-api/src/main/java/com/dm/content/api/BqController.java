package com.dm.content.api;


import com.dm.content.model.po.Bq;
import com.dm.content.service.BqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/bq")
public class BqController {
    @Autowired
    BqService bqService;

    @ResponseBody
    @RequestMapping("/findBq")
    public List<Bq> findBq(){
        return bqService.findAllBq();
    }

}
