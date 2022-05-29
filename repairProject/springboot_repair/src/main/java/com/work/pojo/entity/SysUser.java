package com.work.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Wenkuo
 * @since 2022-05-06
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty("密码")
    @TableField("`password`")
    private String password;

    @ApiModelProperty("账号状态（0正常 1停用）")
    @TableField("`status`")
    private Integer status;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("手机号")
    @TableField("phonenumber")
    private String phonenumber;

    @ApiModelProperty("用户性别（0男，1女，2未知）")
    @TableField("sex")
    private Integer sex;


    @ApiModelProperty("用户类型（0管理员，1普通用户）")
    @TableField("user_type")
    private Integer userType;

    @ApiModelProperty("创建人的用户id")
    @TableField("create_by")
    private Long createBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    @TableField("update_by")
    private Long updateBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;


}
