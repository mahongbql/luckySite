package com.luckysite.mapper;

import com.luckysite.entity.Post;
import com.luckysite.model.PostsParamModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 获取文章列表
     * @param postsParamModel
     * @return
     */
    List<Post> getPostsList(PostsParamModel postsParamModel);

    /**
     * 获取文章的图片
     * @param postName
     * @return
     */
    String getPostsUrl(@Param("postName") String postName);
}
