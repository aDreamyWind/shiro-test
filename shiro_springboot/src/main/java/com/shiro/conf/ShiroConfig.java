package com.shiro.conf;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.shiro.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小韩
 * @Description
 * @Date 2023/2/20 21:41
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        // 1.创建DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 2、创建加密对象，设置相关属性
        HashedCredentialsMatcher macher = new HashedCredentialsMatcher();
        // 2.1、采用MD5加密、迭代加密
        macher.setHashAlgorithmName("md5");
        macher.setHashIterations(3);
        // 3、将加密对象存储到myRealm中
        myRealm.setCredentialsMatcher(macher);
        // 4、将myRealm存入到DefaultWebSecurityManager中
        defaultWebSecurityManager.setRealm(myRealm);
        // 设置rememberMe
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        // 5、返回
        return defaultWebSecurityManager;
    }

    public SimpleCookie rememberMeCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 跨域
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30*24*60*60);
        return cookie;
    }

    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("123456778908".getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        // 设置不认证可以访问的资源
        definition.addPathDefinition("/myController/userLogin", "anon");
        definition.addPathDefinition("/myController/login", "anon");
        // 登出过滤器
        definition.addPathDefinition("/logout", "logout");
        // 设置要拦截的
        definition.addPathDefinition("/**", "authc");
        // 存在用户的过滤器
        definition.addPathDefinition("/**", "user");
        return definition;
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
