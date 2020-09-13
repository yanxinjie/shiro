package com.yxj.shiro.controller;

import com.yxj.shiro.domain.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 严新捷
 * @Type VideoController.java
 * @Desc
 * @date 2020/9/13 15:51
 */
@RestController
@RequestMapping("video")
public class VideoController {


    @RequestMapping("/update")
    public JsonData updateVideo(){

        return JsonData.buildSuccess("video更新成功");

    }

}

