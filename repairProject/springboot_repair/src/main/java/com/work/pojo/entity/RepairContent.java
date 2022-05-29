package com.work.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 报修内容表
 * </p>
 *
 * @author Wenkuo
 * @since 2022-05-06
 */
@Getter
@Setter
@TableName("repair_content")
@ApiModel(value = "RepairContent对象", description = "报修内容表")
public class RepairContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("报修用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("分类id")
    @TableField("classify_id")
    private Integer classifyId;

    @ApiModelProperty("报修内容描述")
    @TableField("`describe`")
    private String describe;

    @ApiModelProperty("报修地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("联系人姓名")
    @TableField("liaison_name")
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    @TableField("liaison_phone")
    private String liaisonPhone;

    @ApiModelProperty("处理状态（1：未处理，2：处理中，3：处理完成）")
    @TableField("state")
    private Integer state;

    @ApiModelProperty("处理管理员id")
    @TableField("sys_user_id")
    private Integer sysUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;


}
