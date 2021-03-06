package com.luckysite.service;

import com.luckysite.entity.FunctionShow;
import com.luckysite.entity.Pic;
import com.luckysite.entity.Post;
import com.luckysite.entity.User;
import com.luckysite.model.UserDataModel;

import java.util.List;

public interface UserService {
    /**
     * 插入一个用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 通过用户名获取一个用户
     * @param userName
     * @return
     */
    User getByUserName(String userName);

    /**
     * 通过用户名获取一个用户
     * @param userId
     * @return
     */
    User getByUserId(int userId);

    /**
     * 跟新用户信息
     * @param user
     */
    void updateLoginInfo(User user);

    /**
     * 获取用户图片信息
     * @param userDataModel
     * @return
     */
    List<Pic> getImage(UserDataModel userDataModel);

    /**
     * 获取用户文章信息
     * @param userDataModel
     * @return
     */
    List<Post> getPosts(UserDataModel userDataModel);

    /**
     * 用户申请升级
     * @param userId
     */
    boolean upVipLevel(Long userId);

    /**
     * 用户是否申请过
     * @param userId
     * @return
     */
    boolean haveApplied(Long userId);

    /**
     * 完善用户信息
     * @param userDataModel
     */
    void perfectUserInfo(UserDataModel userDataModel);

    /**
     * 获取功能展示情况
     * @return
     */
    List<FunctionShow> getFunctionShow();
}
