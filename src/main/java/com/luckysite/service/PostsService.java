package com.luckysite.service;

import com.luckysite.entity.Post;

public interface PostsService {


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
