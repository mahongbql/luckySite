package com.luckysite.service.impl;

import com.github.pagehelper.PageHelper;
import com.luckysite.entity.Pic;
import com.luckysite.mapper.GetImageMapper;
import com.luckysite.model.PicParamModel;
import com.luckysite.model.PicResultModel;
import com.luckysite.service.GetImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mahongbin on 2018/11/24.
 */
@Service
public class GetImageServiceImpl implements GetImageService {

    @Resource
    private GetImageMapper getImageMapper;

    @Override
    public List<Pic> getImage(PicParamModel picParamModel) {
        //使用分页插件,核心代码就这一行
        PageHelper.startPage(picParamModel.getPageNum(), picParamModel.getPageSize());

        return getImageMapper.getImage(picParamModel);
    }

    @Override
    public List<PicResultModel> getImageById(PicParamModel picParamModel) {
        return getImageMapper.getImageById(picParamModel);
    }
}
