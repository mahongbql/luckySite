package com.luckysite.service;

import com.luckysite.entity.Pic;
import com.luckysite.entity.Post;
import com.luckysite.entity.PostPic;
import com.luckysite.entity.User;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface FileService {
    /**
     * 插入图片信息
     * @param path
     * @param userId
     * @param des
     * @param uploadId
     * @param type
     */
    void insertPic(String path, Long userId, String des, Long uploadId, int type);

    /**
     * 上传文件
     * @param file
     * @return 文件地址
     */
    String uploadFile(MultipartFile file) throws Exception;

    /**
     * 保存文章图片地址
     * @param postPic
     */
    void insertPostPic(PostPic postPic);
}
