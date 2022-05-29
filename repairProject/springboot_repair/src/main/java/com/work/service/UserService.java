package com.work.service;

import com.work.pojo.ResponseResult;
import com.work.pojo.dto.UserAddDTO;
import com.work.pojo.dto.UserGetListDTO;

/**
 * @author Wenkuo
 * @Date 2022/05/06 下午 7:00
 */

public interface UserService {

    ResponseResult getList(UserGetListDTO dto);

    ResponseResult getRoleList();

    ResponseResult addUser(UserAddDTO dto);

    ResponseResult deleteUser(Integer id);

    ResponseResult updateUser(UserAddDTO dto);

    ResponseResult setStatus(Integer id);

    ResponseResult resetPwd(Integer id);

    ResponseResult adminLogin(String userName, String password);

    ResponseResult getMenuList();

    ResponseResult adminLogout();
}
