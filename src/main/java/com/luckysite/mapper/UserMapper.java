package com.luckysite.mapper;

import com.luckysite.entity.Pic;
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
}
