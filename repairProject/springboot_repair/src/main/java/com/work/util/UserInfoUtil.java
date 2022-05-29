package com.work.util;

import com.work.pojo.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 从安全作用域中取出已登录的用户信息
 */
public class UserInfoUtil {

    /**
     * 从安全作用域中取出已登录的用户信息
     * @return
     */
    public static LoginUser getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser;
        try {
            loginUser = (LoginUser) authentication.getPrincipal();
        }catch (Exception e){
            loginUser=null;
        }
        return loginUser;
    }

    public static Integer getUserId(){
        Integer id;
        LoginUser userInfo = getUserInfo();
        try {
            id = userInfo.getUser().getId();
        }catch (Exception e){
            id=null;
        }
        return id;
    }
}