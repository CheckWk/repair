package com.work.service;

import com.work.pojo.ResponseResult;
import com.work.pojo.dto.RepairGetListDTO;

/**
 * @author Wenkuo
 * @Date 2022/05/14 下午 12:26
 */

public interface RepairService {
    ResponseResult getList(RepairGetListDTO repairGetListDTO);

    ResponseResult getClassify();

    ResponseResult deleteRepair(Integer id);

    ResponseResult upState(Integer id, Integer state);
}
