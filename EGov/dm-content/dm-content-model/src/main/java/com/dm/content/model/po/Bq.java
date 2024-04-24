package com.dm.content.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_bq")
public class Bq {
    private static final long serialVersionUID = 1L;
    int bq_id;
    String bq;

    public Bq(int bq_id, String bq) {
        this.bq_id = bq_id;
        this.bq = bq;
    }

    public Bq() {
    }
}
