package com.work.mapper;

import com.work.pojo.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Wenkuo
 * @since 2022-05-06
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    //查询用户相关菜单
    List<SysMenu> selectMenus(Integer userId);

    //查询用户相关权限
    List<String> selectPermsByUserId(Integer userId);
}
