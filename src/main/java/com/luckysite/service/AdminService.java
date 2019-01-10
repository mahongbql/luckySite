package com.luckysite.service;

import com.luckysite.entity.Pic;
import com.luckysite.entity.Post;
import com.luckysite.entity.UpLevel;
import com.luckysite.entity.User;
import com.luckysite.model.AdminPicModel;
import com.luckysite.model.AdminPostsModel;
import com.luckysite.model.AdminUserModel;

import java.util.List;

public interface AdminService {
    /**
     * 获取图片列表
     * @param adminPicModel
     * @return
     */
    List<Pic> getPicList(AdminPicModel adminPicModel);

    /**
     * 获取用户列表
     * @param adminUserModel
     * @return
     */
    List<UpLevel> getUserList(AdminUserModel adminUserModel);

    /**
     * 修改用户等级
     * @param adminUserModel
     */
    void updateUserStatus(AdminUserModel adminUserModel);

    /**
     * 修改图片状态
     * @param adminPicModel
     */
    void updatePicStatus(AdminPicModel adminPicModel);

    /**
     * 获取文章列表
     * @param adminPostsModel
     * @return
     */
    List<Post> getPostsList(AdminPostsModel adminPostsModel);
}
