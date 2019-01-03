package com.luckysite.service.impl;

import com.luckysite.entity.Post;
import com.luckysite.mapper.PostsMapper;
import com.luckysite.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsMapper postsMapper;

    @Override
    public void insertPost(Post post) {
        postsMapper.insertPost(post);
    }

    @Override
    public Post searchPost(String post_name) {
        return postsMapper.searchPost(post_name);
    }

    @Override
    public void updataPost(Post post) {
        postsMapper.updataPost(post);
    }
}
