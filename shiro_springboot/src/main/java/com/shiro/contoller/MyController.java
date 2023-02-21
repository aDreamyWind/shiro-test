package com.shiro.contoller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author 小韩
 * @Description
 * @Date 2023/2/20 21:49
 */
@Controller
@RequestMapping("myController")
public class MyController {

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("userLogin")
    public String userLogin(String name, String pwd,
                            @RequestParam(defaultValue = "false") boolean rememberMe,
                            HttpSession session) {
        // 获取subject对象
        Subject subject = SecurityUtils.getSubject();
        // 封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(name, pwd, rememberMe);
        // 调用login
        try {
            subject.login(token);
            session.setAttribute("user", token.getPrincipal().toString());
            return "main";
        } catch (AuthenticationException e) {
            return "登陆失败";
        }
    }

    @GetMapping("userLoginRm")
    public String userLogin(HttpSession session) {
        session.setAttribute("user", "rememberMe");
        return "main";
    }

    // 登陆认证验证角色
    // @RequiresRoles("admin")
    // @GetMapping("userLoginRoles")
    // @RequestBody
    // public String userLoginRoles() {
    //     System.out.println("登陆验证角色成功");
    //     return "登陆验证角色成功";
    // }
}
