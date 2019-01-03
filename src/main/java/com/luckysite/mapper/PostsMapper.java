package com.luckysite.mapper;

import com.luckysite.entity.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostsMapper {
    /**
     * 插入一篇文章
     * @param post
     */
    void insertPost(Post post);

    /**
     * 通过文件名字查文章
     * @param post_name
     * @return
     */
    Post searchPost(String post_name);

    /**
     * 修改文章
     * @param post
     */
    void updataPost(Post post);
}
