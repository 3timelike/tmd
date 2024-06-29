package org.example.control;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.example.mapper.OrderPileMapper;
import org.example.pojo.OrderBack;
import org.example.pojo.OrderFloower;
import org.example.pojo.OrderPile;
import org.example.pojo.vo.PortalVo;
import org.example.service.OrderBackService;
import org.example.service.OrderFloowersService;
import org.example.service.OrderPileService;
import org.example.utils.BaseContext;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderFloowersService orderFloowersService;
    @Resource
    private OrderPileService orderPileService;
    @PostMapping("/updateOrder")
    public Result<?> updateOrder(@RequestBody OrderFloower order) {
        System.out.println(order);
        int i = orderFloowersService.updateOrder(order);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("修改失败");
        }
    }
    @PostMapping("/updateOrderPile")
    public Result<?> updateOrderPile(@RequestBody OrderPile order) {
        System.out.println(order);
        int i = orderPileService.updateOrderPile(order);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("修改失败");
        }
    }
    @Autowired
    OrderBackService backService;
    @PostMapping("/removeOrder")
    public Result<?> removeOrder(@RequestBody OrderBack order) {

        System.out.println(order);
        int i = orderFloowersService.removeOrder(order);
        if (i == 1) {
            System.out.println("删除成功");
            int j = backService.removeOrder(order);
            if(j == 1){
                return Result.ok();
            }else return Result.error("删除失败");

        } else {
            return Result.error("删除失败");
        }
    }

    @PostMapping("/removeOrderPile")
    public Result<?> removeOrderPile(@RequestBody OrderBack order) {
        int i = orderPileService.removeOrder(order);
        if (i == 1) {
            System.out.println("删除成功");
            int j = backService.removeOrder(order);
            if(j == 1){
                return Result.ok();
            }else return Result.error("删除失败");

        } else {
            return Result.error("删除失败");
        }
    }
    @PostMapping("/findOrderPage")
    public Result<?> findOrderPage(@RequestBody PortalVo portalVo) {
        Result result = orderFloowersService.findALlOrder(portalVo);
        return result;
    }

    @PostMapping("/findOrderPagePile")
    public Result<?> findOrderPagePile(@RequestBody PortalVo portalVo) {
        Result result = orderPileService.findALlOrder(portalVo);
        return result;
    }

    @PostMapping("/findUserOrder")
    public Result<?> findUserOrder(@RequestBody PortalVo portalVo,HttpSession session ) {
        System.out.println(111);
        Result result = orderFloowersService.findUserOrder(portalVo, session);
        return result;
    }
    @PostMapping("/findUserOrderPile")
    public Result<?> findUserOrderPile(@RequestBody PortalVo portalVo,HttpSession session ) {
        System.out.println("花堆");
        Result result = orderPileService.findUserOrder(portalVo, session);
        return result;
    }
}
