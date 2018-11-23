package com.luckysite.service.impl;

import com.luckysite.entity.User;
import com.luckysite.mapper.UserMapper;
import com.luckysite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User get(String userName) {
        return userMapper.get(userName);
    }

    @Override
    public User get(int userId) {
        return userMapper.get(userId);
    }
}
