package org.example.service;

import jakarta.servlet.http.HttpSession;
import org.example.pojo.OrderBack;
import org.example.pojo.OrderPile;
import org.example.pojo.vo.PortalVo;
import org.example.utils.Result;

/**
 * ClassName: OrderPileService
 * Package: org.example.service
 * Description:
 *
 * @Author
 * @Create 2024/6/27 18:25
 * @Version 1.0
 */
public interface OrderPileService {
    Result findUserOrder(PortalVo portalVo, HttpSession session);

    int removeOrder(OrderBack order);

    int updateOrderPile(OrderPile order);

    Result findALlOrder(PortalVo portalVo);
}
