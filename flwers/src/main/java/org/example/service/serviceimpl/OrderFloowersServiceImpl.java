package org.example.service.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.pojo.OrderBack;
import org.example.pojo.OrderFloower;
import org.example.pojo.User;
import org.example.pojo.vo.PortalVo;
import org.example.service.OrderFloowersService;
import org.example.mapper.OrderFloowersMapper;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

/**
* @author zhang
* @description 针对表【order_floowers】的数据库操作Service实现
* @createDate 2024-05-24 20:27:16
*/
@Service
public class OrderFloowersServiceImpl implements OrderFloowersService{

    @Autowired
    private OrderFloowersMapper orderFloowersMapper;
    @Override
    public int buyFloower(OrderFloower orderFloower) {
        int i = orderFloowersMapper.buyFloower(orderFloower);
        return i;
    }

    @Override
    public Result findALlOrder(PortalVo portalVo) {
        //Page -> 当前页数  页容量
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        orderFloowersMapper.selectAllOrder(page,portalVo);
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
    public int updateOrder(OrderFloower order) {
        int i = orderFloowersMapper.updateOrderInfo(order);
        return i;
    }

    @Override
    public int removeOrder(OrderBack order) {
        int i = orderFloowersMapper.removeOrder(order);
        return i;
    }

    @Override
    public Result findUserOrder(PortalVo portalVo , HttpSession session) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        User o = (User) session.getAttribute("User");
        orderFloowersMapper.selectUserOrders(page,portalVo, Integer.valueOf(o.getUid()));
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




