package com.luckysite.mapper;

import com.luckysite.entity.Pic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Mapper
public interface FileMapper {
    /**
     * 插入图片信息
     * @param pic
     */
    void insertPic(Pic pic);
}
