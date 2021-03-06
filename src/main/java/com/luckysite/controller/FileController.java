package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AuthConfig;
import com.luckysite.common.enums.PostPicStatusEnum;
import com.luckysite.common.enums.ResultCode;
import com.luckysite.dto.login.LoginDataDTO;
import com.luckysite.entity.PostPic;
import com.luckysite.model.Result;
import com.luckysite.service.FileService;
import com.luckysite.util.RedisUtil;
import com.luckysite.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * 上传图片到fastdfs
 */
@RestController
@RequestMapping("/fileUpload")
public class FileController {

    private Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/upload")
    @Auth(role = AuthConfig.VIP)
    public Result fdfsUpload(@RequestParam("file") MultipartFile file,  @RequestParam("token") String token,
                             @RequestParam("des") String des, @RequestParam("uploadId") String uploadId,
                             @RequestParam("type") Integer type) {
        if (file.isEmpty()) {
            return ResultUtil.error(ResultCode.ERROR.getCode(), "File can not be null !", null);
        }

        log.info("FileController-fdfsUpload-获取到信息为：token：" + token +
                ", des：" + des +
                ", uploadId：" + uploadId);
        try {
            String fileUrl = fileService.uploadFile(file);

            if(null == fileUrl){
                throw new Exception("path is null Exception");
            }

            LoginDataDTO loginDataDTO = (LoginDataDTO) redisUtil.get(token);
            fileService.insertPic(fileUrl, loginDataDTO.getUserId(), des, Long.parseLong(uploadId), type);

            log.info("FileUploadController-fdfsUpload-上传文件返回地址：" + fileUrl);

            HashMap<String, Object> result = new HashMap<>();
            result.put("uploadId", uploadId);

            return ResultUtil.success(result);
        } catch (Exception ex) {
            log.error("FileController-fdfsUpload-上传文件失败：" + ex);
            return ResultUtil.error(ResultCode.ERROR.getCode(), "文件上传失败", null);
        }
    }

    /**
     * 上传文章的图片
     * @param file
     * @param upload_name
     * @return
     */
    @PostMapping("/uploadPostPic")
    @Auth(role = AuthConfig.AUTHOR)
    public Result uploadPostPic(@RequestParam("file") MultipartFile file, @RequestParam("upload_name") String upload_name,
                                @RequestParam("userId") String userId) {
        if (file.isEmpty()) {
            return ResultUtil.error(ResultCode.ERROR.getCode(), "File can not be null !", null);
        }

        log.info("userId :" + userId + "upload_name: " + upload_name);

        try {
            String fileUrl = fileService.uploadFile(file);

            if(null == fileUrl){
                throw new Exception("path is null Exception");
            }

            PostPic postPic = new PostPic();
            postPic.setUploadName(upload_name);
            postPic.setUrl(fileUrl);
            postPic.setUserId(Long.parseLong(userId));
            postPic.setStatus(PostPicStatusEnum.USING.getStatus());
            fileService.insertPostPic(postPic);

            HashMap<String, Object> result = new HashMap<>();
            result.put("fileUrl", fileUrl);
            return ResultUtil.success(result);
        }catch (Exception ex) {
            log.error("FileController-uploadPostPic-上传文件失败：" + ex);
            return ResultUtil.error(ResultCode.ERROR.getCode(), "文件上传失败", null);
        }
    }
}
