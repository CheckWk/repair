package com.work.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用来接收前端表单数据
 * @author Wenkuo
 * @Date 2022/05/14 下午 11:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetListDTO {
    @ApiModelProperty("开始页")
    private Integer pageNum;

    @ApiModelProperty("页大小")
    private Integer pageSize;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("手机号")
    private String phonenumber;

    @ApiModelProperty("账号状态（0正常 1停用）")
    private Integer status;

    @ApiModelProperty("用户类型（0管理员，1普通用户）")
    private Integer userType;
}
