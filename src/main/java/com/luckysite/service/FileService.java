package com.luckysite.service;

import com.luckysite.entity.Pic;
import com.luckysite.entity.User;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface FileService {
    /**
     * 插入图片信息
     * @param path
     * @param user
     */
    void insertPic(String path, User user, String des, Long uploadId);

    /**
     * 上传文件
     * @param file
     * @return 文件地址
     */
    String uploadFile(MultipartFile file) throws Exception;
}
