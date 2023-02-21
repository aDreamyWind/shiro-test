package com.shiro.conf;

import com.shiro.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
        // 5、返回
        return defaultWebSecurityManager;
    }

    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        // 设置不认证可以访问的资源
        definition.addPathDefinition("/myController/userLogin", "anon");
        definition.addPathDefinition("/myController/login", "anon");
        // 设置要拦截的
        definition.addPathDefinition("/**", "authc");
        return definition;
    }
}
