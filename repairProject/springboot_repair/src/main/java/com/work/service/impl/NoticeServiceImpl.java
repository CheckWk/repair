package com.work.service.impl;

import com.work.mapper.NoticeMapper;
import com.work.pojo.ResponseResult;
import com.work.pojo.entity.Notice;
import com.work.service.NoticeService;
import com.work.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wenkuo
 * @Date 2022/05/07 下午 6:12
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    

    @Override
    public ResponseResult add(String title, String content) {
        //封装实体类对象
        Notice notice=new Notice();
        notice.setContent(content);
        notice.setTitle(title);
        //获取执行该操作的用户id，封装进实体类中
        Integer userId = UserInfoUtil.getUserId();
        notice.setUserId(userId);
        //执行插入语句
        int insert = noticeMapper.insert(notice);
        if (insert==0){
            //保存失败
            return ResponseResult.errorResult(400,"公告保存失败！");
        }
        // 保存成功时返回的结果
        return ResponseResult.okResult("公告保存成功！");
    }

    @Override
    public ResponseResult findAll() {
        //查询所有公告
        List<Notice> notices = noticeMapper.selectList(null);
        return ResponseResult.okResult(notices);
    }

    @Override
    public ResponseResult deleteNotice(Integer id) {
        int i = noticeMapper.deleteById(id);
        if (i==1){
            // 删除成功时返回的结果
            return ResponseResult.okResult();
        }
        //删除失败
        return ResponseResult.errorResult(400,"公告删除失败！");
    }
}
