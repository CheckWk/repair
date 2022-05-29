package com.work.controller;

import com.work.pojo.ResponseResult;
import com.work.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api("公告接口")
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @ApiOperation("添加公告")
    @PostMapping("/add")
    public ResponseResult add(@ApiParam("标题") String title,
                              @ApiParam("内容") String content){
        return noticeService.add(title,content);
    }

    @ApiOperation("查询公告")
    @GetMapping("/findAll")
    public ResponseResult findAll(){
        return noticeService.findAll();
    }


    @ApiOperation("删除公告")
    @GetMapping("/delete/{id}")
    public ResponseResult deleteRepair(@PathVariable("id") Integer id ){
        return noticeService.deleteNotice(id);
    }

}
