package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AuthConfig;
import com.luckysite.enmu.ResultCode;
import com.luckysite.entity.User;
import com.luckysite.model.Result;
import com.luckysite.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 上传图片到fastdfs
 */
@RestController
@RequestMapping("/fileUpload")
public class FileController {

    private Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @Auth(role = AuthConfig.USER)
    public Result fdfsUpload(@RequestParam("file") MultipartFile file,  @RequestParam("userId") int userId,
                             @RequestParam("des") String des, @RequestParam("uploadId") Long uploadId,
                             RedirectAttributes redirectAttributes, HttpSession httpSession) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return new Result(ResultCode.ERROR.getCode(), "File can not be null !", null);
        }

        try {
            String fileUrl = fileService.uploadFile(file);

            if(null == fileUrl){
                throw new Exception("path is null Exception");
            }

            User user = (User) httpSession.getAttribute(userId+"");
            fileService.insertPic(fileUrl, user, des, uploadId);

            log.info("FileUploadController-fdfsUpload-上传文件返回地址：" + fileUrl);
            return new Result(ResultCode.SUCCESS.getCode(), "success", fileUrl);
        } catch (Exception ex) {
            log.error("FileController-fdfsUpload-上传文件失败：" + ex);
            return new Result(ResultCode.ERROR.getCode(), "文件上传失败", null);
        }
    }
}
