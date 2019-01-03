package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AuthConfig;
import com.luckysite.enmu.PostStatusEnmu;
import com.luckysite.entity.Post;
import com.luckysite.model.Result;
import com.luckysite.service.PostsService;
import com.luckysite.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private Logger log = LoggerFactory.getLogger(PostsController.class);

    @Autowired
    private PostsService postsService;

    @PostMapping("/uploadPost")
    @Auth(role = AuthConfig.AUTHOR)
    public Result uploadPost(@RequestParam("content") String content, @RequestParam("upload_name") String upload_name,
                             @RequestParam("userId") String userId) {
        Post post = postsService.searchPost(upload_name);

        if(null == post){
            post = new Post();
            post.setContent(content);
            post.setPost_name(upload_name);
            post.setStatus(PostStatusEnmu.APPLICATION.getStatus());
            post.setUserId(Long.parseLong(userId));

            postsService.insertPost(post);
            log.info("posts-uploadPost-文章添加：用户【" + userId + "】");
        }else{
            post.setContent(content);
            postsService.updataPost(post);
            log.info("posts-uploadPost-文章修改：用户【" + userId + "】");
        }

        return ResultUtil.success();
    }
}
