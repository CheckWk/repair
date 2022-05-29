package com.work.controller;

import com.work.enums.AppHttpCodeEnum;
import com.work.pojo.ResponseResult;
import com.work.pojo.dto.RepairGetListDTO;
import com.work.service.RepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wenkuo
 * @Date 2022/05/14 下午 12:13
 */
@Api("报修列表接口")
@RestController
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;

    /**
     * 查询所有报修分类
     * @return
     */
    @ApiOperation("查询所有报修分类")
    @GetMapping("/getClassify")
    public ResponseResult getClassify(){
        return repairService.getClassify();
    }

    @ApiOperation("分页查询报修记录")
    @PostMapping("/getList")
    public ResponseResult getList(RepairGetListDTO repairGetListDTO){
        return repairService.getList(repairGetListDTO);
    }

    //删除报修记录
    @ApiOperation("删除报修记录")
    @GetMapping("/delete/{id}")
    public ResponseResult deleteRepair(@PathVariable("id") Integer id ){
        return repairService.deleteRepair(id);
    }

    //修改处理状态
    @ApiOperation("修改处理状态")
    @GetMapping("/upState/{id}/{state}")
    public ResponseResult upState(@PathVariable("id") Integer id,@PathVariable("state") Integer state ){
        //（1：未处理，2：处理中，3：处理完成）
        if (state==1 || state==2 || state==3){
            return repairService.upState(id,state);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"修改状态错误！");
    }

}
