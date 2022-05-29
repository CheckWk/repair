package com.work.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wenkuo
 * @Date 2022/05/15 下午 10:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleListVo {
    @ApiModelProperty("角色id")
    private Integer id;

    @ApiModelProperty("角色名称")
    private String name;

}
