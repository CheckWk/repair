package com.work.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 从配置文件中读取配置的默认密码
 * @author Wenkuo
 * @Date 2022/05/20 上午 9:15
 */
@Component
public class PasswordConfig {
    //读取配置文件中的默认密码
    @Value("${person.password}")
    public String defaultPwd;
}
