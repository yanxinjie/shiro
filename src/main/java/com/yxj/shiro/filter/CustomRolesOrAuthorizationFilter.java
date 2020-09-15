package com.yxj.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author 严新捷 自定义Filter
 * @Type CustomRolesOrAuthorizationFilter.java
 * @Desc
 * @date 2020/9/13 17:26
 */
public class CustomRolesOrAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {

        Subject subject = getSubject(req, resp);

        //获取当前访问路径所需要的角色集合
        String[] rolesArray = (String[]) mappedValue;

        //没有角色限制，有权限访问
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }

        //当前subject是rolesArray中的任何一个，则有权限访问
        for (int i = 0; i < rolesArray.length; i++) {
            if (subject.hasRole(rolesArray[i])) {
                return true;
            }
        }

        return false;
    }
}
