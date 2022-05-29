package com.work.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.work.pojo.vo.RoleListVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用来接收前端表单数据
 * @author Wenkuo
 * @Date 2022/05/15 下午 3:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDTO {
    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String phonenumber;

    @ApiModelProperty("用户性别（0男，1女，2未知）")
    private Integer sex;

    @ApiModelProperty("用户类型（0管理员，1普通用户）")
    @TableField("user_type")
    private Integer userType;

    @ApiModelProperty("角色列表")
    private Integer[] roleList;

}
