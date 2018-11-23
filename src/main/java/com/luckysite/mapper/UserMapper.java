package com.luckysite.mapper;

import com.luckysite.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    User get(String userName);

    User get(int userId);
}
