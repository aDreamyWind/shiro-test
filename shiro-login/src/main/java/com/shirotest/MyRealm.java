package com.shirotest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author 小韩
 * @Description 自定义登陆认证
 * @Date 2023/2/19 22:43
 */
public class MyRealm extends AuthenticatingRealm {

    // 自定义登陆认证方法，shiro的login方法底层会调用该类的认证方法进行认证
    // 需要配置自定义的realm生效，在ini文件中配置，在springboot中配置
    // 该方法只是获取进行对比的信息，认证逻辑还是按照shiro底层认证逻辑完成

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1、获取身份信息
        String principal = authenticationToken.getPrincipal().toString();
        // 2、获取凭证信息
        String password = new String((char[]) authenticationToken.getCredentials());

        System.out.println("认证用户信息1：" + principal);
        System.out.println("认证用户信息2：" + password);
        // 3、获取数据库中存储的信息
        if(principal.equals("zhangsan")) {
            // 3.1 数据库中存储的加盐3次迭代的密码
            String pwdInfo = "7174f64b13022acd3c56e2781e098a5f";
            // 4、创建封装校验逻辑对象，封装数据返回
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),     // 身份信息
                    pwdInfo,   // 密码
                    ByteSource.Util.bytes("salt"),    // 盐
                    authenticationToken.getPrincipal().toString()
            );
            System.out.println("info: " + info);
            return info;
        }
        return null;
    }
}
