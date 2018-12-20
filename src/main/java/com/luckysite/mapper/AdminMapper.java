package com.luckysite.mapper;

import com.luckysite.entity.Pic;
import com.luckysite.entity.UpLevel;
import com.luckysite.entity.User;
import com.luckysite.model.AdminPicModel;
import com.luckysite.model.AdminUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 修改用户等级（uplevel）
     * @param upLevel
     */
    void updateUpLevelStatus(UpLevel upLevel);

    /**
     * 修改用户等级（user_msg）
     * @param role
     */
    void updateUserRole(@Param("role") Integer role, @Param("userId") int userId);

    /**
     * 修改图片状态
     * @param adminPicModel
     */
    void updatePicStatus(AdminPicModel adminPicModel);
}
