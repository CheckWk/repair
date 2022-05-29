package com.work.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 查询用户列表返回的对象
 * @author Wenkuo
 * @Date 2022/05/17 下午 4:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("昵称")
    @TableField("nick_name")
    private String nickName;


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

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("用户类型（0管理员，1普通用户）")
    @TableField("user_type")
    private Integer userType;


    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("角色信息")
    private List<RoleListVo> roles;
}
