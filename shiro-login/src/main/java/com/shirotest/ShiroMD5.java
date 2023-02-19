package com.shirotest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author 小韩
 * @Description 加密
 * @Date 2023/2/19 22:33
 */
public class ShiroMD5 {
    public static void main(String[] args) {
        // 密码明文加密
        String password = "z3";
        // 使用MD5加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println(md5Hash);

        // 带盐的md5加密
        Md5Hash saltMd5 = new Md5Hash(password, "salt");
        System.out.println(saltMd5);

        // 带盐的md5多次迭代加密
        Md5Hash saltMoreMd5 = new Md5Hash(password, "salt", 3);
        System.out.println(saltMoreMd5);

        // 使用父类加密
        SimpleHash simpleHash = new SimpleHash("MD5", password, "salt", 3);
        System.out.println(simpleHash);
    }
}
