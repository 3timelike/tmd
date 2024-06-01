package org.example.service.serviceimpl;




import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.pojo.SSign;
import org.example.pojo.vo.PortalVo;
import org.example.service.SSignService;
import org.example.mapper.SSignMapper;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author zhang
* @description 针对表【s_sign】的数据库操作Service实现
* @createDate 2024-05-21 13:29:20
*/
@Service
public class SSignServiceImpl implements SSignService{
    @Autowired
    private SSignMapper signMapper;
    @Override
    public int signup(String username,String name) {

        ArrayList<SSign> o = signMapper.selectAll(username);
        if (!o.isEmpty()) return -1;
        int i = signMapper.signup(username,name);
        return i;
    }

    @Override
    public Result findSignUpPage(PortalVo portalVo) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        signMapper.selectTodayUser(page,portalVo);
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
    public Result findNoSignUpPage(PortalVo portalVo) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        signMapper.selectTodayNoUser(page,portalVo);
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
}




