package com.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.work.enums.AppHttpCodeEnum;
import com.work.mapper.RepairClassifyMapper;
import com.work.mapper.RepairContentMapper;
import com.work.pojo.ResponseResult;
import com.work.pojo.dto.RepairGetListDTO;
import com.work.pojo.entity.RepairClassify;
import com.work.pojo.entity.RepairContent;
import com.work.pojo.vo.PageVo;
import com.work.service.RepairService;
import com.work.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wenkuo
 * @Date 2022/05/14 下午 12:27
 */
@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    private RepairContentMapper repairContentMapper;

    @Autowired
    private RepairClassifyMapper repairClassifyMapper;

    /**
     * 根据条件分页查询
     * @param repairGetListDTO
     * @return
     */
    @Override
    public ResponseResult getList(RepairGetListDTO repairGetListDTO) {
        //根据前端传过来的开始页、页大小构造分页构造器
        Page<RepairContent> page=new Page<>(repairGetListDTO.getPageNum(),repairGetListDTO.getPageSize());
        LambdaQueryWrapper<RepairContent> wrapper=new LambdaQueryWrapper<>();
        //判断前端传过来的条件是否为空，根据条件拼接SQL
        if (repairGetListDTO.getLiaisonName() != null) {
            //判断联系人姓名是否为空
            wrapper.like(RepairContent::getLiaisonName,repairGetListDTO.getLiaisonName());
        }
        if (repairGetListDTO.getLiaisonPhone() != null) {
            //判断联系人电话是否为空
            wrapper.like(RepairContent::getLiaisonPhone,repairGetListDTO.getLiaisonPhone());
        }
        if (repairGetListDTO.getState() !=0) {
            //判断处理状态是否不为0，前端不选择时默认为0
            wrapper.eq(RepairContent::getState,repairGetListDTO.getState());
        }
        if (repairGetListDTO.getClassifyId() !=0) {
            //判断分类id是否不为0，前端不进行选择时默认为0
            wrapper.eq(RepairContent::getClassifyId,repairGetListDTO.getClassifyId());
        }
        //分页查询
        Page<RepairContent> contentPage = repairContentMapper.selectPage(page, wrapper);
        return ResponseResult.okResult( PageVo.createPageVo(contentPage));
    }

    /**
     * 查询报修分类
     * @return
     */
    @Override
    public ResponseResult getClassify() {
        //查询全部的分类表中的全部内容
        List<RepairClassify> classifyList = repairClassifyMapper.selectList(null);
        return ResponseResult.okResult(classifyList);
    }

    /**
     * 根据报修记录删除
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteRepair(Integer id) {

        int i = repairContentMapper.deleteById(id);
        if (i == 1) {
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"删除失败！");
    }

    /**
     * 修改报修处理状态
     * @param id
     * @param state
     * @return
     */
    @Override
    public ResponseResult upState(Integer id, Integer state) {
        //封装实体类
        RepairContent repairContent = new RepairContent();
        repairContent.setId(id);
        repairContent.setState(state);
        //获取执行该操作的管理员id，封装进实体类中
        Integer userId = UserInfoUtil.getUserId();
        repairContent.setSysUserId(userId);
        //mybatisPlus自带更新语句，会自动根据表中主键更新
        int i = repairContentMapper.updateById(repairContent);

        if (i == 1) {
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"更改状态失败！");

    }
}
