package com.yxj.shiro.mapper;

import com.yxj.shiro.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 严新捷
 * @Type PermissionMapper.java
 * @Desc
 * @date 2020/9/13 14:33
 */
@Mapper
public interface PermissionMapper {
    @Select("select p.id as id, p.name as name, p.url as url from  role_permission rp " +
            "left join permission p on rp.permission_id=p.id " +
            "where  rp.role_id= #{roleId} ")
    List<Permission> findPermissionListByRoleId(@Param("roleId") int roleId);
}
