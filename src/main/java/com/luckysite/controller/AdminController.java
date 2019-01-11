package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AuthConfig;
import com.luckysite.enmu.PostStatusEnmu;
import com.luckysite.entity.Pic;
import com.luckysite.entity.Post;
import com.luckysite.entity.UpLevel;
import com.luckysite.model.AdminPicModel;
import com.luckysite.model.AdminPostsModel;
import com.luckysite.model.AdminUserModel;
import com.luckysite.model.Result;
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

    @Auth(role = AuthConfig.ADMIN)
    @RequestMapping("/updatePicStatus")
    @ResponseBody
    public Result updatePicStatus(AdminPicModel adminPicModel){
        adminService.updatePicStatus(adminPicModel);

        return ResultUtil.success();
    }

    @Auth(role = AuthConfig.ADMIN)
    @RequestMapping("/updateUserStatus")
    @ResponseBody
    public Result updateUserStatus(AdminUserModel adminUserModel){
        adminService.updateUserStatus(adminUserModel);

        return ResultUtil.success();
    }

    @Auth(role = AuthConfig.ADMIN)
    @RequestMapping("/getPostsList")
    @ResponseBody
    public Result getPostsList(AdminPostsModel adminPostsModel){
        adminPostsModel.setStatus(PostStatusEnmu.APPLICATION.getStatus());
        List<Post> postList = adminService.getPostsList(adminPostsModel);

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", postList);

        return ResultUtil.success(result);
    }

    @Auth(role = AuthConfig.ADMIN)
    @RequestMapping("/updatePostsStatus")
    @ResponseBody
    public Result updatePostsStatus(AdminPostsModel adminPostsModel){
        adminService.updatePostsStatus(adminPostsModel);

        return ResultUtil.success();
    }
}
