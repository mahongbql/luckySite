package com.luckysite.mapper;

import com.luckysite.entity.Pic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by mahongbin on 2018/11/24.
 */
@Mapper
public interface GetImageMapper {
    /**
     * 获取所有图片信息
     * @return
     */
    List<Pic> getImage();
}
