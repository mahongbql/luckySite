package com.luckysite.service;

import com.luckysite.entity.User;

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
     * 通过token获取用户信息
     * @param token
     * @return
     */
    User getByToken(String token);

    /**
     * 跟新用户信息
     * @param user
     */
    void updateLoginInfo(User user);
}
