package com.work.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用来接收前端表单数据
 * @author Wenkuo
 * @Date 2022/05/14 下午 12:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairGetListDTO {
    @ApiModelProperty("开始页")
    private Integer pageNum;

    @ApiModelProperty("页大小")
    private Integer pageSize;

    @ApiModelProperty("联系人姓名")
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    private String liaisonPhone;

    @ApiModelProperty("处理状态（1：未处理，2：处理中，3：处理完成）")
    private Integer state;

    @ApiModelProperty("分类id")
    private Integer classifyId;


}
