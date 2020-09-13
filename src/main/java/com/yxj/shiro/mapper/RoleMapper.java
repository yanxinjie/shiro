package com.yxj.shiro.mapper;

import com.yxj.shiro.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author 严新捷
 * @Type RoleMapper.java
 * @Desc
 * @date 2020/9/13 14:35
 */
@Mapper
public interface RoleMapper {
    @Select("select ur.role_id as id, " +
            "r.name as name, " +
            "r.description as description " +
            " from  user_role ur left join role r on ur.role_id = r.id " +
            "where  ur.user_id = #{userId}")
    @Results(
            value = {
                    @Result(id=true, property = "id",column = "id"),
                    @Result(property = "name",column = "name"),
                    @Result(property = "description",column = "description"),
                    @Result(property = "permissionList",column = "id",
                            many = @Many(select = "com.yxj.shiro.mapper.PermissionMapper.findPermissionListByRoleId", fetchType = FetchType.DEFAULT)
                    )
            }
    )
    List<Role> findRoleListByUserId(@Param("userId")int userId);
}
