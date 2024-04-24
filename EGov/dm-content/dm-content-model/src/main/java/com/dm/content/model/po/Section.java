/**
 * @Author: 李幸阜
 * @Date: 2024/4/20 00:19
 * @Description:
 **/

package com.dm.content.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_section")
public class Section implements Serializable {
    private static final long serialVersionUID = 1L;
    Long id;
    String section;
    String sunc;
}
