package org.example.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.example.pojo.FFloowers;
import org.example.pojo.vo.FVo;
import org.example.pojo.vo.PortalVo;
import org.example.service.FFloowersService;
import org.example.mapper.FFloowersMapper;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author zhang
* @description 针对表【f_floowers】的数据库操作Service实现
* @createDate 2024-05-23 19:27:46
*/
@Service
public class FFloowersServiceImpl implements FFloowersService{


    @Autowired
    private FFloowersMapper fFloowersMapper;
    @Override
    public int addNewFloower(FVo fVo) {
        int i = fFloowersMapper.addNewFloower(fVo);

        return i;
    }

    @Override
    public Result findAllFloowers(PortalVo portalVo) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        fFloowersMapper.findAllFloowers(page,portalVo);
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
    public int updateFloower(FVo fVo) {
        int i = fFloowersMapper.updatebyid(fVo);
        return i;
    }

    @Override
    public int removeFloower(FVo fVo) {
        int i = fFloowersMapper.removeFloower(fVo);
        return i;
    }

    @Override
    public int updateFnum(int f_id) {
        int i = fFloowersMapper.updateFnum(f_id);
        return i;
    }

    @Override
    public int updateNewFloower(FFloowers fVo) {
        System.out.println(fVo);
        int i =  fFloowersMapper.updateByf_Id(fVo);
        return i;
    }

    @Override
    public Result findFloowersbyid(int fId) {


        Map<String, String> fFloowers =  fFloowersMapper.selectByf_Id(fId);


        if(fFloowers == null)
             return  Result.error("无数据");

        return Result.ok(fFloowers);
    }
}




