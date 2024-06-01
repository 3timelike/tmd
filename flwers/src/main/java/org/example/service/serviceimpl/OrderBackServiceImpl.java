package org.example.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.example.pojo.OrderBack;
import org.example.service.OrderBackService;
import org.example.mapper.OrderBackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author zhang
* @description 针对表【order_back】的数据库操作Service实现
* @createDate 2024-05-25 14:25:24
*/
@Service
public class OrderBackServiceImpl implements OrderBackService{


    @Autowired
    private OrderBackMapper backMapper;
    @Override
    public int removeOrder(OrderBack back) {
        int i = backMapper.removeOrder(back);
        return i;
    }
}




