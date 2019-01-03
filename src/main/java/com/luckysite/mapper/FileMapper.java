package com.luckysite.mapper;

import com.luckysite.entity.Pic;
import com.luckysite.entity.Post;
import com.luckysite.entity.PostPic;
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

    /**
     * 插入文章图片信息
     * @param postPic
     */
    void insertPostPic(PostPic postPic);

    /**
     * 插入一篇文章
     * @param post
     */
    void insertPost(Post post);
}
