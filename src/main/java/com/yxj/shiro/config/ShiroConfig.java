package com.yxj.shiro.config;

import com.yxj.shiro.filter.CustomRolesOrAuthorizationFilter;
import com.yxj.shiro.realm.PasswordRealm;
import com.yxj.shiro.session.CustomSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 严新捷
 * @Type ShiroConfig.java
 * @Desc
 * @date 2020/9/13 14:24
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("执行 ShiroFilterFactoryBean.shiroFilter()");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //需要登录的接口，如果访问某个接口，需要登录却没登录，则调用此接口(如果不是前后端分离，则跳转页面)
        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");

        //登录成功，跳转url，如果前后端分离，则没这个调用
        shiroFilterFactoryBean.setSuccessUrl("/");

        //没有权限，未授权就会调用此方法， 先验证登录-》再验证是否有权限
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");

        //拦截器路径，坑一，部分路径无法进行拦截，时有时无；因为同学使用的是hashmap, 无序的，应该改为LinkedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 设置自定义filter
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("roleOrFilter",new CustomRolesOrAuthorizationFilter());
        filterChainDefinitionMap.put("/admin/**","roleOrFilter[admin,root]");
        shiroFilterFactoryBean.setFilters(filtersMap);

        //退出过滤器
        filterChainDefinitionMap.put("/logout","logout");

        //匿名可以访问，也是就游客模式
        filterChainDefinitionMap.put("/pub/**","anon");

        //登录用户才可以访问
        filterChainDefinitionMap.put("/authc/**","authc");

        //管理员角色才可以访问
        filterChainDefinitionMap.put("/admin/**","roleOrFilter[admin,root]");

        //有编辑权限才可以访问
        filterChainDefinitionMap.put("/video/update","perms[video_update]");


        //坑二: 过滤链是顺序执行，从上而下，一般讲/** 放到最下面

        //authc : url定义必须通过认证才可以访问
        //anon  : url可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //如果不是前后端分离，则不必设置下面的sessionManager
        securityManager.setSessionManager(sessionManager());

        //使用自定义的cacheManager
        securityManager.setCacheManager(redisCacheManager());

        //设置realm（推荐放到最后，不然某些情况会不生效）
        securityManager.setRealm(passwordRealm());

        return securityManager;
    }


    /**
     * 自定义realm
     * @return
     */
    @Bean
    public PasswordRealm passwordRealm(){
        PasswordRealm passwordRealm = new PasswordRealm();

        passwordRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return passwordRealm;
    }


    /**
     * 密码加解密规则
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        //设置散列算法：这里使用的MD5算法
        credentialsMatcher.setHashAlgorithmName("md5");

        //散列次数，好比散列2次，相当于md5(md5(xxxx))
        credentialsMatcher.setHashIterations(2);

        return credentialsMatcher;
    }



    //自定义sessionManager
    @Bean
    public SessionManager sessionManager(){

        CustomSessionManager customSessionManager = new CustomSessionManager();

        //超时时间，默认 30分钟，会话超时；方法里面的单位是毫秒
//        customSessionManager.setGlobalSessionTimeout(20000);

        // 配置session持久化
        customSessionManager.setSessionDAO(redisSessionDAO());

        return customSessionManager;
    }

    /**
     * 配置redisManager
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("192.168.50.26");
        redisManager.setPort(32790);
        redisManager.setPassword("root123");
        return redisManager;
    }


    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 设置过期时间，单位是秒，建议5min
        redisCacheManager.setExpire(20);
        return redisCacheManager;
    }

    /**
     * 自定义session持久化
     * @return
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        // 设置过期时间，单位是秒，默认是1800s
        redisSessionDAO.setExpire(1800);
        return redisSessionDAO;
    }
}
