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
    User get(String userName);

    /**
     * 通过用户名获取一个用户
     * @param userId
     * @return
     */
    User get(int userId);
}
