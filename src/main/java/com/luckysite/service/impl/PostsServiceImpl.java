package com.luckysite.service.impl;

import com.github.pagehelper.PageHelper;
import com.luckysite.entity.Post;
import com.luckysite.mapper.PostsMapper;
import com.luckysite.model.PostsParamModel;
import com.luckysite.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Post> getPostsList(PostsParamModel postsParamModel) {
        //使用分页插件,核心代码就这一行
        PageHelper.startPage(postsParamModel.getPageNum(), postsParamModel.getPageSize());

        return postsMapper.getPostsList(postsParamModel);
    }

    @Override
    public String getPostsUrl(String postName) {
        return postsMapper.getPostsUrl(postName);
    }
}
