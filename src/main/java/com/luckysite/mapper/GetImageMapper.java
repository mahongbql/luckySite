package com.luckysite.mapper;

import com.luckysite.entity.Pic;
import com.luckysite.model.PicParamModel;
import com.luckysite.model.PicResultModel;
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
    List<Pic> getImage(PicParamModel picParamModel);

    /**
     * 获取同一批次的图片信息
     * @param picParamModel
     * @return
     */
    List<PicResultModel> getImageById(PicParamModel picParamModel);
}
