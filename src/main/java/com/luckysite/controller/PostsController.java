package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AuthConfig;
import com.luckysite.enmu.PostStatusEnmu;
import com.luckysite.entity.Post;
import com.luckysite.model.PostsParamModel;
import com.luckysite.model.Result;
import com.luckysite.service.CacheService;
import com.luckysite.service.PostsService;
import com.luckysite.util.CacheKeyUtil;
import com.luckysite.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private Logger log = LoggerFactory.getLogger(PostsController.class);

    @Autowired
    private CacheService cacheService;

    @Autowired
    private PostsService postsService;

    @PostMapping("/uploadPost")
    @Auth(role = AuthConfig.AUTHOR)
    public Result uploadPost(@RequestParam("content") String content, @RequestParam("upload_name") String upload_name,
                             @RequestParam("userId") String userId, @RequestParam("title") String title,
                             @RequestParam("type") Integer type) {
        Post post = postsService.searchPost(upload_name);

        log.info("-------content-------: " + content);

        if(null == post){
            post = new Post();
            post.setContent(content);
            post.setPost_name(upload_name);
            post.setStatus(PostStatusEnmu.APPLICATION.getStatus());
            post.setUserId(Long.parseLong(userId));
            post.setTitle(title);
            post.setType(type);

            postsService.insertPost(post);
            log.info("posts-uploadPost-文章添加：用户【" + userId + "】");
        }else{
            post.setContent(content);
            post.setTitle(title);
            post.setType(type);
            post.setStatus(PostStatusEnmu.APPLICATION.getStatus());

            postsService.updataPost(post);
            log.info("posts-uploadPost-文章修改：用户【" + userId + "】");
        }

        return ResultUtil.success();
    }

    /**
     * 获取文章详情
     * @param postName
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/getPosts")
    @ResponseBody
    public Result getPosts(@RequestParam("postName") String postName, @RequestParam("userId") String userId){
        Post post = postsService.searchPost(postName);
        post.setCollect(cacheService.checkIsCollect(CacheKeyUtil.POST_COLLECT, post.getId().toString(), userId));

        cacheService.setViewNumber(CacheKeyUtil.POST_VIEW_NUMBER, post.getId().toString());

        Map<String, Object> result = new HashMap<>();
        result.put("data", post);

        return ResultUtil.success(result);
    }

    /**
     * 获取文章详情
     * @param postName
     * @return
     */
    @Auth(role = AuthConfig.AUTHOR)
    @RequestMapping("/authorGetPosts")
    @ResponseBody
    public Result authorGetPosts(@RequestParam("postName") String postName){
        Post post = postsService.searchPost(postName);

        Map<String, Object> result = new HashMap<>();
        result.put("data", post.getContent());

        return ResultUtil.success(result);
    }

    /**
     * 获取文章列表
     * @param postsParamModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/getPostsList")
    @ResponseBody
    public Result getPostsList(PostsParamModel postsParamModel){
        postsParamModel.setStatus(PostStatusEnmu.PASS.getStatus());
        List<Post> postList = postsService.getPostsList(postsParamModel);

        for(Post post : postList){
            post.setViewNumber(cacheService.getViewNumber(CacheKeyUtil.POST_VIEW_NUMBER, post.getId().toString()));
            post.setCollectNumber(cacheService.getCollectNumber(CacheKeyUtil.POST_COLLECT_NUMBER, post.getId().toString()));
            post.setUrl(postsService.getPostsUrl(post.getPost_name()));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("postList", postList);

        return ResultUtil.success(result);
    }

    /**
     * 修改文章收藏状态
     * @param postsParamModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/setCollectStatus")
    @ResponseBody
    public Result setCollectStatus(PostsParamModel postsParamModel){
        Boolean status = cacheService.setCollectNumber(CacheKeyUtil.POST_COLLECT_NUMBER, CacheKeyUtil.POST_COLLECT, postsParamModel.getPostId(),
                postsParamModel.getUserId(), postsParamModel.getCollect());

        Map<String, Object> result = new HashMap<>();
        result.put("status", status);

        return ResultUtil.success(result);
    }
}
