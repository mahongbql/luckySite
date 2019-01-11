package com.luckysite.service.impl;

import com.github.pagehelper.PageHelper;
import com.luckysite.enmu.UpLevelEnmu;
import com.luckysite.enmu.UserTypeEnmu;
import com.luckysite.entity.Pic;
import com.luckysite.entity.Post;
import com.luckysite.entity.UpLevel;
import com.luckysite.entity.User;
import com.luckysite.mapper.AdminMapper;
import com.luckysite.model.AdminPicModel;
import com.luckysite.model.AdminPostsModel;
import com.luckysite.model.AdminUserModel;
import com.luckysite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Pic> getPicList(AdminPicModel adminPicModel) {
        return adminMapper.getPicList(adminPicModel);
    }

    @Override
    public List<UpLevel> getUserList(AdminUserModel adminUserModel) {
        return adminMapper.getUserList(adminUserModel);
    }

    @Override
    @Transactional
    public void updateUserStatus(AdminUserModel adminUserModel) {
        UpLevel upLevel = new UpLevel();
        upLevel.setStatus(UpLevelEnmu.HAS_ADOPTED.getStatus());
        upLevel.setUserId(Long.parseLong(adminUserModel.getUserId()));
        upLevel.setType(adminUserModel.getType());

        adminMapper.updateUpLevelStatus(upLevel);
        adminMapper.updateUserRole(UserTypeEnmu.VIP.getRoleId(), Integer.parseInt(adminUserModel.getUserId()));
    }

    @Override
    public void updatePicStatus(AdminPicModel adminPicModel) {
        adminMapper.updatePicStatus(adminPicModel);
    }

    @Override
    public List<Post> getPostsList(AdminPostsModel adminPostsModel) {
        return adminMapper.getPostsList(adminPostsModel);
    }

    @Override
    public void updatePostsStatus(AdminPostsModel adminPostsModel) {
        adminMapper.updatePostsStatus(adminPostsModel);
    }
}
