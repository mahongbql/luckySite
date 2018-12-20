package com.luckysite.service.impl;

import com.luckysite.enmu.PicStatusEnum;
import com.luckysite.entity.Pic;
import com.luckysite.entity.User;
import com.luckysite.mapper.FileMapper;
import com.luckysite.service.FileService;
import com.luckysite.util.FastDFSClientWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    private Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;

    @Override
    public void insertPic(String path, User user, String des, Long uploadId, String userIcon, int type) {
        Pic pic = new Pic();

        pic.setPath(path);
        pic.setUserId(user.getUserId());
        pic.setDes(des);
        pic.setUserIcon(userIcon);
        pic .setUploadId(uploadId);
        pic.setType(type);
        pic.setStatus(PicStatusEnum.APPLICATION.getStatus());

        fileMapper.insertPic(pic);
        log.info("FileServiceImpl-insertPic：图片插入完成");
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String fileUrl = null;
        try{
            fileUrl= fastDFSClientWrapper.uploadFile(file);
            log.info("FileServiceImpl-uploadFile-上传文件返回地址：" + fileUrl);
        } catch (Exception ex) {
            throw ex;
        }

        return fileUrl;
    }
}
