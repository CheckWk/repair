package com.work.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用来接收前端表单数据
 * @author Wenkuo
 * @Date 2022/05/07 下午 5:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNoticeDTO {


    @ApiModelProperty("公告标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("公告内容")
    @TableField("content")
    private String content;
}
