package com.work.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.work.mapper.SysMenuMapper;
import com.work.mapper.SysUserMapper;
import com.work.pojo.LoginUser;
import com.work.pojo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * SpringSecurity中处理用户登录
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysMenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName,username);
        queryWrapper.eq(SysUser::getUserType,0);//用户类型必须为管理员
        queryWrapper.eq(SysUser::getStatus,0);//账号状态必须为可用状态
        SysUser user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        //  根据用户权限查询，添加到UserDetails中
//        List<String> list=new ArrayList<>(Arrays.asList("test"));
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        //封装UserDetails并返回结果
        return new LoginUser(user,list);
    }
}
