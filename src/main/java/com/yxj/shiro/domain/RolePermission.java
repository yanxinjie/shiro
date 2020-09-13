package com.yxj.shiro.domain;

/**
 * @author 严新捷
 * @Type RolePermission.java
 * @Desc
 * @date 2020/9/13 14:30
 */
public class RolePermission {

    private int id;

    private int roleId;

    private int permissionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
