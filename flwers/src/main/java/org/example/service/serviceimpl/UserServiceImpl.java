package org.example.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.pojo.vo.PortalVo;
import org.example.pojo.vo.UserVo;
import org.example.service.UserService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserMapper userMapper;
    @Override
    public User Userlogin(String username, String password) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", username);
        qw.eq("password", password);
        User user = userMapper.selectOne(qw);

        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public int updateNewStudent(User user) {
        int i = userMapper.updateById(user);
        return i;
    }

    @Override
    public Result findALlUser(PortalVo portalVo) {
        //Page -> 当前页数  页容量
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        userMapper.selectAllUser(page,portalVo);
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
    public int regist(User user) {
        int i = userMapper.insert(user);
        return i;
    }


}
