package com.shiro.contoller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 小韩
 * @Description
 * @Date 2023/2/21 23:05
 */
@ControllerAdvice
public class PermissionsException {
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String unaEx(Exception e) {
        return "无权限";
    }

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationExce(Exception e) {
        return "无权限";
    }
}
