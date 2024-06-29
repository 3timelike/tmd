package org.example.service.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.mapper.LeaveMessageMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Message;
import org.example.pojo.User;
import org.example.pojo.vo.MessageVo;
import org.example.pojo.vo.PortalVo;
import org.example.service.LeaveMessageService;
import org.example.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: LeaveMessageServiceImpl
 * Package: org.example.service.serviceimpl
 * Description:
 *
 * @Author
 * @Create 2024/6/29 9:51
 * @Version 1.0
 */
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {
    @Resource
    private LeaveMessageMapper leaveMessageMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public IPage<MessageVo> getPage(int currentPage, int pageSize) {
        IPage<Message> page = new Page(currentPage,pageSize);
        leaveMessageMapper.selectPage(page,null);

        IPage<MessageVo> newPage = new Page<>();
        BeanUtils.copyProperties(page,newPage);

        for (MessageVo messageVo : newPage.getRecords()){
            User user = userMapper.selectById(messageVo.getFrom());
            messageVo.setUser(user);
        }

        return newPage;
    }

    @Override
    public Result getAll(PortalVo portalVo) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        leaveMessageMapper.selectListSelf(page,portalVo);

        Map data = new HashMap();
        data.put("pageData",page.getRecords());
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPage",page.getPages());
        data.put("totalSize",page.getTotal());

        Map pageInfo = new HashMap();
        pageInfo.put("pageInfo",data);
        return Result.ok(pageInfo);
    }

    @Override
    public int insertMessage(Message message) {
        int i =leaveMessageMapper.insertSelf(message);
        return i;
    }

    @Override
    public int deleteMessage(Integer id) {
        int i = leaveMessageMapper.deleteById(id);
        return i;
    }
}
