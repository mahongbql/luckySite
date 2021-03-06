package com.luckysite.service.impl;

import com.luckysite.config.FdfsConfig;
import com.luckysite.common.enums.PicStatusEnum;
import com.luckysite.entity.Pic;
import com.luckysite.entity.PostPic;
import com.luckysite.mapper.FileMapper;
import com.luckysite.service.FileService;
import com.luckysite.util.FastDFSClientWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;

    @Autowired
    private FdfsConfig fdfsConfig;

    @Override
    public void insertPic(String path, Long userId, String des, Long uploadId, int type) {
        Pic pic = new Pic();

        pic.setPath(path);
        pic.setUserId(userId);
        pic.setDes(des);
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

    @Override
    public void insertPostPic(PostPic postPic) {
        fileMapper.insertPostPic(postPic);
    }
}
