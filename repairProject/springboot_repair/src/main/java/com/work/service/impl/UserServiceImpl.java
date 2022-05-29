package com.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.work.config.PasswordConfig;
import com.work.enums.AppHttpCodeEnum;
import com.work.mapper.SysMenuMapper;
import com.work.mapper.SysRoleMapper;
import com.work.mapper.SysUserMapper;
import com.work.mapper.SysUserRoleMapper;
import com.work.pojo.LoginUser;
import com.work.pojo.ResponseResult;
import com.work.pojo.dto.UserAddDTO;
import com.work.pojo.dto.UserGetListDTO;
import com.work.pojo.entity.SysMenu;
import com.work.pojo.entity.SysRole;
import com.work.pojo.entity.SysUser;
import com.work.pojo.entity.SysUserRole;
import com.work.pojo.vo.MenuVo;
import com.work.pojo.vo.PageVo;
import com.work.pojo.vo.RoleListVo;
import com.work.pojo.vo.UserVo;
import com.work.service.UserService;
import com.work.util.BeanCopyUtils;
import com.work.util.JwtUtil;
import com.work.util.RedisCache;
import com.work.util.UserInfoUtil;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Wenkuo
 * @Date 2022/05/14 下午 11:23
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysMenuMapper menuMapper;

    //从配置文件中读取默认密码
    @Autowired
    PasswordConfig passwordConfig;

    //用户认证
    @Autowired
    private AuthenticationManager authenticationManager;
    //Redis工具类
    @Autowired
    private RedisCache redisCache;

    /**
     * 根据角色id列表，给用户封装多个角色信息
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @Transactional //添加事务全部成功或失败
    public boolean addRoles(Integer userId, Integer[] roleIds) {
        for (Integer roleId : roleIds) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            int insert1 = sysUserRoleMapper.insert(sysUserRole);
            if (insert1 == 0) {
                // 添加失败
                return false;
            }
        }
        //添加成功
        return true;
    }

    /**
     * 根据用户名查询用户是否存在
     * @param userName
     * @return true表示存在，false表示不存在
     */
    public boolean isExitByUserName(String userName){
        //先查询数据库看用户名是否已经存在
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName, userName);
        SysUser queryUser = userMapper.selectOne(queryWrapper);
        if (queryUser != null) {
            // 用户已存在，返回错误提示信息
            return true;
        }
        return false;
    }


    /**
     * 获取用户列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult getList(UserGetListDTO dto) {
        Page<SysUser> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        //拼接查询条件
        if (StringUtils.isNotBlank(dto.getUserName())) { //判断用户名是否为空(长度不为0并且字符串不是空格)
            wrapper.like(SysUser::getUserName, dto.getUserName());
        }
        if (StringUtils.isNotBlank(dto.getPhonenumber())) { //判断电话号是否为空(长度不为0并且字符串不是空格)
            wrapper.like(SysUser::getPhonenumber, dto.getPhonenumber());
        }
        //用户类型（0管理员，1普通用户）
        Integer userType = dto.getUserType();
        if (userType != null && (userType == 0 || userType == 1)) { //判断用户类型是否为空
            wrapper.eq(SysUser::getUserType, userType);
        }
        //账号状态（0正常 1停用）
        Integer status = dto.getStatus();
        if (status != null && (status == 0 || status == 1)) { //判断用户状态是否为空
            wrapper.eq(SysUser::getStatus, status);
        }
        //分页查询用户列表
        Page<SysUser> userPage = userMapper.selectPage(page, wrapper);
        //将除“结果集”以外的分页的数据封装到PageVo中
        PageVo<UserVo> userVoPageVo = PageVo.createPageVoNoItems(userPage, UserVo.class);
        //封装前端需要的UserVo数据
        List<UserVo> userVos = BeanCopyUtils.copyList(userPage.getRecords(), UserVo.class);
        //TODO 查询并封装用户角色信息（感觉可以优化）
        for (UserVo userVo : userVos) {
            //根据用户id查询对应的角色
            List<SysRole> sysRoles = sysUserRoleMapper.selectRoleByUserId(userVo.getId());
            //将角色信息封装成前端需要的Vo对象
            List<RoleListVo> roleListVos = BeanCopyUtils.copyList(sysRoles, RoleListVo.class);
            //将角色列表赋值给前端需要的用户对象中
            userVo.setRoles(roleListVos);
        }
        userVoPageVo.setItems(userVos);
        return ResponseResult.okResult(userVoPageVo);
    }

    /**
     * 查询全部的角色信息
     *
     * @return
     */
    @Override
    public ResponseResult getRoleList() {
        //查询全部的角色信息
        List<SysRole> sysRoles = roleMapper.selectList(null);
        //把查询到的角色信息封装成需要返回的对象
        List<RoleListVo> roleListVos = BeanCopyUtils.copyList(sysRoles, RoleListVo.class);
        return ResponseResult.okResult(roleListVos);
    }

    /**
     * 添加用户
     *
     * @param dto
     * @return
     */
    @Transactional
    @Override
    public ResponseResult addUser(UserAddDTO dto) {
        // 把接收到的参数封装成实体类
        SysUser user = BeanCopyUtils.copy(dto, SysUser.class);
        //先查询数据库看用户名是否已经存在
        boolean exitByUserName = isExitByUserName(user.getUserName());
        if (exitByUserName) {
            // 用户已存在，返回错误提示信息
            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST, "用户已存在！");
        }
        //将用户信息保存到数据库中
        //初始化默认密码
        String defaultPwd=passwordConfig.defaultPwd;
        //对密码加密
        //new一个BCryptPasswordEncoder对象
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(defaultPwd));
        //将数据保存到数据库中
        int insert = userMapper.insert(user);
        if (insert == 0) {
            // 返回错误提示信息
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, "添加失败！");
        }



        //给该用户封装角色信息
        Integer userId = user.getId();//插入成功后用户id会自动存在 在对象中
        Integer[] roleIds = dto.getRoleList();
        if (!addRoles(userId, roleIds)) {
            // 返回错误提示信息
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, "添加角色信息失败！");
        }
        return ResponseResult.okResult();
    }



    @Override
    public ResponseResult deleteUser(Integer id) {
        int row = userMapper.deleteById(id);
        if (row == 0) {
            // 返回错误提示信息
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, "删除失败！");
        }
        return ResponseResult.okResult();
    }

    /**
     * 修改用户
     * @param dto
     * @return
     */
    @Transactional
    @Override
    public ResponseResult updateUser(UserAddDTO dto) {
        SysUser user = BeanCopyUtils.copy(dto, SysUser.class);
        user.setId(dto.getUserId());
        user.setUserName(null);
        /* 用户名不允许修改
        //先查询数据库看用户名是否已经存在
        boolean exitByUserName = isExitByUserName(user.getUserName());
        if (exitByUserName) {
            // 用户已存在，返回错误提示信息
            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST, "用户已存在！");
        }*/
        int i = userMapper.updateById(user);
        if (i!=0){
            //删除用户权限关联表中，有关该用户的所有信息
            LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysUserRole::getUserId,user.getId());
            int delete = sysUserRoleMapper.delete(wrapper);
            System.out.println("删除权限关联表中数据条数："+delete);
            //重新添加用户权限信息
            boolean b = addRoles(user.getId(), dto.getRoleList());
            if (b){
                return ResponseResult.okResult();
            }
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"修改失败！");
    }

    /**
     * 通过userId修改禁用状态
     * @param id
     * @return
     */
    @Override
    public ResponseResult setStatus(Integer id) {
        int i = userMapper.reverseStatus(id);
        if (i==1){
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"修改状态错误！");
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @Override
    public ResponseResult resetPwd(Integer id) {
        SysUser user=new SysUser();
        user.setId(id);
        //读取配置文件中的默认密码

//        PasswordConfig passwordConfig = new PasswordConfig();
        String defaultPwd=passwordConfig.defaultPwd;

        //对密码加密
        //new一个BCryptPasswordEncoder对象
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(defaultPwd));
        int i = userMapper.updateById(user);
        if (i==1){
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"重置密码失败！");

    }



    /**
     * 管理员登录
     * @return
     */
    @Override
    public ResponseResult adminLogin(String userName,String password) {
        // 通过AuthenticationManager中的authenticate来进行认证
        Authentication passwordAuthenticationToken=new UsernamePasswordAuthenticationToken(userName,password);
        Authentication authenticate = authenticationManager.authenticate(passwordAuthenticationToken);
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }

        //如果认证通过了，使用userid生成一个jwt，jwt存入ResponseResult中返回给前端
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Integer userid = loginUser.getUser().getId();
        String token = JwtUtil.createJWT(userid + "");
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        //查询用户对应的菜单
        List<SysMenu> sysMenus = menuMapper.selectMenus(userid);
        List<MenuVo> menuVos = BeanCopyUtils.copyList(sysMenus, MenuVo.class);
        map.put("menuList",menuVos);
        //将数据存入redis缓存中
        redisCache.setCacheObject("login:"+userid,loginUser);
        return new ResponseResult(200,"登录成功",map);
    }

    @Override
    public ResponseResult getMenuList() {
        //调用工具类获取用户的id
        Integer userId = UserInfoUtil.getUserId();

        //查询用户对应的菜单
        List<SysMenu> sysMenus = menuMapper.selectMenus(userId);
        List<MenuVo> menuVos = BeanCopyUtils.copyList(sysMenus, MenuVo.class);

        return ResponseResult.okResult(menuVos);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public ResponseResult adminLogout() {
        Integer userId = UserInfoUtil.getUserId();
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.okResult();
    }
}
