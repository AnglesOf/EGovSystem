package com.dm.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.content.model.po.Section;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface SectionDao extends BaseMapper<Section> {
    String selctFunc(String section);
}
