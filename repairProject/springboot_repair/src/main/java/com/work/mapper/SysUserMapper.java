package com.work.mapper;

import com.work.pojo.entity.SysMenu;
import com.work.pojo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Wenkuo
 * @since 2022-05-06
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    //取反状态
    int reverseStatus(Integer userId);

}
