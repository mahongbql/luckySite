package com.luckysite.mapper;

import com.luckysite.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    User getByUserName(@Param("userName") String userName);

    User getByUserId(@Param("userId") int userId);

    void updateLoginInfo(User user);
}
