package org.example.service.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.example.mapper.OrderPileMapper;
import org.example.pojo.OrderBack;
import org.example.pojo.OrderPile;
import org.example.pojo.User;
import org.example.pojo.vo.PortalVo;
import org.example.service.OrderPileService;
import org.example.utils.Result;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: OrderPileServiceImpl
 * Package: org.example.service.serviceimpl
 * Description:
 *
 * @Author
 * @Create 2024/6/27 18:25
 * @Version 1.0
 */
@Service
public class OrderPileServiceImpl implements OrderPileService {

    @Resource
    private OrderPileMapper orderPileMapper;

    @Override
    public Result findUserOrder(PortalVo portalVo , HttpSession session) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        User o = (User) session.getAttribute("User");
        orderPileMapper.selectUserOrders(page,portalVo, Integer.valueOf(o.getUid()));
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
    public int removeOrder(OrderBack order) {
        return orderPileMapper.removeOrder(order);
    }

    @Override
    public int updateOrderPile(OrderPile order) {
        return orderPileMapper.updateOrderInfo(order);
    }

    @Override
    public Result findALlOrder(PortalVo portalVo) {
        //Page -> 当前页数  页容量
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        orderPileMapper.selectAllOrder(page,portalVo);
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
