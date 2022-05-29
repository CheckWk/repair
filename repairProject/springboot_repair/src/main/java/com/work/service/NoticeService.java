package com.work.service;

import com.work.pojo.ResponseResult;

/**
 * @author Wenkuo
 * @Date 2022/05/07 下午 6:11
 */

public interface NoticeService {
    ResponseResult add(String title, String content);

    ResponseResult findAll();

    ResponseResult deleteNotice(Integer id);
}
