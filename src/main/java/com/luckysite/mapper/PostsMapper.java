package com.luckysite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luckysite.entity.Post;
import com.luckysite.model.PostsParamModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostsMapper extends BaseMapper<Post> {
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

    /**
     * 清除指定批次的图片
     * @param upload_name
     */
    void clearPostPictures(@Param("upload_name") String upload_name);

    /**
     * 获取指定批次名称图片的路径
     * @param upload_name
     * @return
     */
    List<String> getPostPicList(@Param("upload_name") String upload_name);
}
