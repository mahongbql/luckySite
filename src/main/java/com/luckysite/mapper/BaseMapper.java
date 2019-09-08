package com.luckysite.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author mahongbin
 * @date 2019/6/20 13:52
 * @Description
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
