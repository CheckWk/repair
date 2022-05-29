package com.work.mapper;

import com.work.pojo.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wenkuo
 * @since 2022-05-06
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
