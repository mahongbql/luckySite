package com.luckysite.service.impl;

import com.github.pagehelper.PageHelper;
import com.luckysite.enmu.UpLevelEnmu;
import com.luckysite.entity.Pic;
import com.luckysite.entity.UpLevel;
import com.luckysite.entity.User;
import com.luckysite.mapper.AdminMapper;
import com.luckysite.model.AdminPicModel;
import com.luckysite.model.AdminUserModel;
import com.luckysite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Pic> getPicList(AdminPicModel adminPicModel) {
        //使用分页插件,核心代码就这一行
        PageHelper.startPage(adminPicModel.getPageNum(), adminPicModel.getPageSize());

        return adminMapper.getPicList(adminPicModel);
    }

    @Override
    public List<UpLevel> getUserList(AdminUserModel adminUserModel) {
        //使用分页插件,核心代码就这一行
        PageHelper.startPage(adminUserModel.getPageNum(), adminUserModel.getPageSize());

        return adminMapper.getUserList(adminUserModel);
    }

    @Override
    public void updateUserStatus(AdminUserModel adminUserModel) {
        UpLevel upLevel = new UpLevel();
        upLevel.setStatus(adminUserModel.getStatus());
        upLevel.setUserId(Long.parseLong(adminUserModel.getUserId()));
        upLevel.setType(adminUserModel.getType());

        adminMapper.updateUpLevelStatus(upLevel);
        adminMapper.updateUserRole(adminUserModel.getStatus());
    }

    @Override
    public void updatePicStatus(AdminPicModel adminPicModel) {
        adminMapper.updatePicStatus(adminPicModel);
    }
}
