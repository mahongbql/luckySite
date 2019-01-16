package com.luckysite.mapper;

import com.luckysite.entity.Pic;
import com.luckysite.entity.Post;
import com.luckysite.entity.UpLevel;
import com.luckysite.entity.User;
import com.luckysite.model.UserDataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    User getByUserName(@Param("userName") String userName);

    User getByUserId(@Param("userId") int userId);

    User getByToken(@Param("token") String token);

    void updateLoginInfo(User user);

    List<Pic> getImage(UserDataModel userDataModel);

    void upVipLevel(UpLevel upLevel);

    UpLevel getUpLevel(@Param("userId") Long userId);

    /**
     * 完善用户信息
     * @param userDataModel
     */
    void perfectUserInfo(UserDataModel userDataModel);

    /**
     * 获取用户文章信息
     * @param userDataModel
     * @return
     */
    List<Post> getPosts(UserDataModel userDataModel);
}
