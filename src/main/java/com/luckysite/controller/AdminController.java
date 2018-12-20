package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AuthConfig;
import com.luckysite.entity.Pic;
import com.luckysite.entity.UpLevel;
import com.luckysite.entity.User;
import com.luckysite.model.AdminPicModel;
import com.luckysite.model.AdminUserModel;
import com.luckysite.model.Result;
import com.luckysite.model.UserDataModel;
import com.luckysite.service.AdminService;
import com.luckysite.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Auth(role = AuthConfig.ADMIN)
    @RequestMapping("/showPicList")
    @ResponseBody
    public Result showPicList(AdminPicModel adminPicModel){
        HashMap<String, Object> result = new HashMap<>();

        List<Pic> picList = adminService.getPicList(adminPicModel);
        result.put("data", picList);

        return ResultUtil.success(result);
    }

    @Auth(role = AuthConfig.ADMIN)
    @RequestMapping("/showUserList")
    @ResponseBody
    public Result showUserList(AdminUserModel adminUserModel){
        HashMap<String, Object> result = new HashMap<>();

        List<UpLevel> userList = adminService.getUserList(adminUserModel);
        result.put("data", userList);

        return ResultUtil.success(result);
    }


}
