package com.shiro.contoller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 小韩
 * @Description
 * @Date 2023/2/20 21:49
 */
@Controller
@RequestMapping("myController")
public class MyController {

    @GetMapping("userLogin")
    @ResponseBody
    public String userLogin(String name, String pwd) {
        // 获取subject对象
        Subject subject = SecurityUtils.getSubject();
        // 封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(name, pwd);
        // 调用login
        try {
            subject.login(token);
            return "登陆成功";
        } catch (AuthenticationException e) {
            return "登陆失败";
        }
    }
}
