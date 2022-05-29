package com.work.controller;

import com.work.enums.AppHttpCodeEnum;
import com.work.pojo.ResponseResult;
import com.work.pojo.dto.UserAddDTO;
import com.work.pojo.dto.UserGetListDTO;
import com.work.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Wenkuo
 * @Date 2022/05/06 下午 6:59
 */
@Api("用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("退出登录")
    @GetMapping("/adminLogout")
    public ResponseResult adminLogout(){
        return userService.adminLogout();
    }

    @ApiOperation("管理员登录")
    @PostMapping("/adminLogin")
    public ResponseResult adminLogin(String userName,String password){
        if (userName == null || password==null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR,"用户名或密码不能为空");
        }
        return userService.adminLogin(userName,password);
    }

    /**
     * 根据dto中的条件分页查询用户列表
     * @param dto
     * @return
     */
    @ApiOperation("分页查询用户列表")
    @PostMapping("/getUserList")
    public ResponseResult getUserList(UserGetListDTO dto){
        return userService.getList(dto);
    }

    /**
     * 查询所有用户角色
     * @return
     */
    @ApiOperation("查询所有用户角色")
    @GetMapping("/getRoleList")
    public ResponseResult getRoleList(){
        return userService.getRoleList();
    }

    /**
     * 添加或修改用户
     * @param dto
     * @return
     */
    @ApiOperation("添加或修改用户")
    @PostMapping("/addUser")
    public ResponseResult addUser(UserAddDTO dto){
        //判断用户id是否为空
        if (dto.getUserId() == null) {
            //添加用户
            return userService.addUser(dto);
        }else {
            //修改用户
            return userService.updateUser(dto);
        }
    }

    //根据用户获取不同的菜单列表
    @ApiOperation("获取菜单列表")
    @GetMapping("/getMenu")
    public ResponseResult getMenu(){
        return userService.getMenuList();
    }

    //删除用户
    @ApiOperation("删除用户")
    @GetMapping("/delete/{id}")
    public ResponseResult deleteUser(@PathVariable("id") Integer id ){
        return userService.deleteUser(id);
    }

    //修改禁用状态
    @ApiOperation("修改用户状态")
    @GetMapping("/setStatus/{id}")
    public ResponseResult setStatus(@PathVariable("id") Integer id ){
        return userService.setStatus(id);
    }

    //重置密码
    @ApiOperation("重置密码")
    @GetMapping("/resetPwd/{id}")
    public ResponseResult resetPwd(@PathVariable("id") Integer id ){
        return userService.resetPwd(id);
    }
}
