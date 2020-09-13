package com.yxj.shiro.mapper;

import com.yxj.shiro.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 严新捷
 * @Type UserMapper.java
 * @Desc
 * @date 2020/9/13 14:36
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUsername(@Param("username")String username);



    @Select("select * from user where id=#{userId}")
    User findById(@Param("userId") int id);



    @Select("select * from user where username = #{username} and password = #{pwd}")
    User findByUsernameAndPwd(@Param("username")String username, @Param("pwd")String pwd);
}
