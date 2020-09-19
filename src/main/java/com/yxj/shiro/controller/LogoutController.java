package com.yxj.shiro.controller;

import com.yxj.shiro.domain.JsonData;
import com.yxj.shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author 严新捷
 * @Type LogoutController.java
 * @Desc
 * @date 2020/9/15 22:22
 */
@RestController
public class LogoutController {


    @RequestMapping("/logout")
    public JsonData findMyPlayRecord(){
        return JsonData.buildSuccess("logout成功");
    }

}
