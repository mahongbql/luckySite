package com.luckysite.service.impl;

import com.github.pagehelper.PageHelper;
import com.luckysite.config.AuthConfig;
import com.luckysite.common.enums.UpLevelEnum;
import com.luckysite.common.enums.UpLevelTypeEnum;
import com.luckysite.entity.*;
import com.luckysite.mapper.FunctionShowMapper;
import com.luckysite.mapper.UserMapper;
import com.luckysite.model.UserDataModel;
import com.luckysite.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private FunctionShowMapper functionShowMapper;

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
    public List<Post> getPosts(UserDataModel userDataModel) {
        PageHelper.startPage(userDataModel.getPageNum(), userDataModel.getPageSize());
        return userMapper.getPosts(userDataModel);
    }

    @Override
    public boolean upVipLevel(Long userId) {
        UpLevel upLevel = new UpLevel();
        boolean status = false;

        User user = userMapper.getByUserId(Integer.parseInt(userId+""));

        if(user.getRole() == AuthConfig.AUTHOR){
            log.info("UserServiceImpl-upVipLevel-用户【"+userId+"】已经是最高级");
            return true;
        }

        upLevel.setUserId(userId);
        upLevel.setStatus(UpLevelEnum.APPLICATION.getStatus());

        switch (user.getRole()){
            case 1:
                upLevel.setType(UpLevelTypeEnum.UP_LEVEL_TO_VIP.getType());
                break;
            case 2:
                upLevel.setType(UpLevelTypeEnum.UP_LEVEL_TO_AUTHOR.getType());
                break;
        }

        userMapper.upVipLevel(upLevel);

        return status;
    }

    @Override
    public boolean haveApplied(Long userId) {
        UpLevel upLevel = userMapper.getUpLevel(userId);

        return upLevel == null ? false : true;
    }

    @Override
    public void perfectUserInfo(UserDataModel userDataModel) {
        userMapper.perfectUserInfo(userDataModel);
    }

    @Override
    public List<FunctionShow> getFunctionShow() {
        return functionShowMapper.selectAll();
    }
}
