package com.shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @author 小韩
 * @Description
 * @Date 2023/2/19 21:02
 */
public class ShiroRun {
    public static void main(String[] args) {
        // 1.初始化获取SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 2.获取Subject对象
        Subject subject = SecurityUtils.getSubject();

        // 3.创建token对象，web应用用户名密码从页面传递
        AuthenticationToken token = new UsernamePasswordToken("zhangsan", "z3");

        // 4.完成登陆
        try {
            subject.login(token);
            System.out.println("登陆成功");

            // 5.判断是否拥有角色
            boolean hasRole = subject.hasRole("role1");
            System.out.println("是否拥有角色" + hasRole);

            // 6.判断是否有权限
            boolean permitted = subject.isPermitted("user:insert");
            System.out.println("判断是否有权限" + permitted);
            subject.checkPermission("user:insert");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        } catch (UnauthorizedException e) {
            e.printStackTrace();
            System.out.println("无权限");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("失败");
        }
    }
}
