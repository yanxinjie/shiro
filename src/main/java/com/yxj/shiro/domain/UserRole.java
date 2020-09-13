package com.yxj.shiro.domain;

/**
 * @author 严新捷
 * @Type UserRole.java
 * @Desc
 * @date 2020/9/13 14:31
 */
public class UserRole {

    private int id;

    private int userId;

    private int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
