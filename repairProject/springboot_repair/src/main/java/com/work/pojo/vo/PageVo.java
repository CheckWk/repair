package com.work.pojo.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> {

    @ApiModelProperty("当前页")
    private Integer currentPage = 1;

    @ApiModelProperty("每页显示的总条数")
    private Integer pageSize = 10;

    @ApiModelProperty("总条数")
    private Long totalNum;

    @ApiModelProperty("是否有下一页")
    private boolean hasNextPage;

    @ApiModelProperty("总页数")
    private Long totalPage;

    @ApiModelProperty("分页结果")
    private List<T> items;


    /**
     * 将page中除“分页结果”的数据封装进PageVo对象
     * @param page
     * @param vClass
     * @param <V>
     * @return
     */
    public static <V> PageVo<V> createPageVoNoItems(Page page,Class<V> vClass){
        // 封装PageVo
        return new PageVo<V>((int) page.getCurrent(),(int) page.getSize(),page.getTotal(),page.hasNext(),page.getPages(),null);
    }

    /**
     * 将page对象封装成一样类型的PageVo
     * @param page
     * @param <T>
     * @return
     */
    public static <T> PageVo<T> createPageVo(Page<T> page){
        // 封装PageVo
        return new PageVo<T>((int) page.getCurrent(),(int) page.getSize(),page.getTotal(),page.hasNext(),page.getPages(),page.getRecords());
    }

}