package com.luckysite.service.impl;

import com.github.pagehelper.PageHelper;
import com.luckysite.enmu.UpLevelEnmu;
import com.luckysite.enmu.UpLevelTypeEnmu;
import com.luckysite.entity.Pic;
import com.luckysite.entity.UpLevel;
import com.luckysite.entity.User;
import com.luckysite.mapper.UserMapper;
import com.luckysite.model.UserDataModel;
import com.luckysite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getByUserName(String userName) {
        return userMapper.getByUserName(userName);
    }

    @Override
    public User getByUserId(int userId) {
        return userMapper.getByUserId(userId);
    }

    @Override
    public User getByToken(String token) {
        return userMapper.getByToken(token);
    }

    @Override
    public void updateLoginInfo(User user) {
        userMapper.updateLoginInfo(user);
    }

    @Override
    public List<Pic> getImage(UserDataModel userDataModel) {
        //使用分页插件,核心代码就这一行
        PageHelper.startPage(userDataModel.getPageNum(), userDataModel.getPageSize());

        return userMapper.getImage(userDataModel);
    }

    @Override
    public void upVipLevel(Long userId) {
        UpLevel upLevel = new UpLevel();

        User user = userMapper.getByUserId(Integer.parseInt(userId+""));

        upLevel.setUserId(userId);
        upLevel.setStatus(UpLevelEnmu.APPLICATION.getStatus());

        switch (user.getRole()){
            case 1:
                upLevel.setType(UpLevelTypeEnmu.UP_LEVEL_TO_VIP.getType());
                break;
        }

        userMapper.upVipLevel(upLevel);
    }
}
