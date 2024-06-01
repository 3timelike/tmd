package org.example.service;


import org.example.pojo.OrderBack;

/**
* @author zhang
* @description 针对表【order_back】的数据库操作Service
* @createDate 2024-05-25 14:25:24
*/
public interface OrderBackService  {
   int removeOrder(OrderBack back);
}
