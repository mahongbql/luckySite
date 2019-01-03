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
     * @param user
     * @param des
     * @param uploadId
     * @param userIcon
     * @param type
     */
    void insertPic(String path, User user, String des, Long uploadId, String userIcon, int type);

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

    /**
     * 插入一篇文章
     * @param post
     */
    void insertPost(Post post);
}
