package com.work.mapper;

import com.work.pojo.entity.SysRole;
import com.work.pojo.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wenkuo
 * @since 2022-05-06
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<SysRole> selectRoleByUserId(Integer userId);
}
