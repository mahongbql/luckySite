package com.luckysite.mapper;

import com.luckysite.entity.Pic;
import com.luckysite.entity.UpLevel;
import com.luckysite.entity.User;
import com.luckysite.model.AdminPicModel;
import com.luckysite.model.AdminUserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
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
}
