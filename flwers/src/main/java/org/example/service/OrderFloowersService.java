package org.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.OrderBack;
import org.example.pojo.OrderFloower;
import org.example.pojo.vo.PortalVo;
import org.example.utils.Result;

/**
* @author zhang
* @description 针对表【order_floowers】的数据库操作Service
* @createDate 2024-05-24 20:27:16
*/
public interface OrderFloowersService  {


    int buyFloower(OrderFloower orderFloower);

    Result findALlOrder(PortalVo portalVo);

    int updateOrder(OrderFloower order);

    int removeOrder(OrderBack order);
}
