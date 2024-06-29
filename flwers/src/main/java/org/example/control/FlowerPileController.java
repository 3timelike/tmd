package org.example.control;

import jakarta.annotation.Resource;
import org.example.mapper.OrderPileMapper;
import org.example.pojo.FlowerPile;
import org.example.pojo.OrderPile;
import org.example.pojo.vo.PortalVo;
import org.example.service.FlowerPileService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * ClassName: FlowerPileController
 * Package: org.example.control
 * Description:
 *
 * @Author
 * @Create 2024/6/26 19:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/flowerPile")
public class FlowerPileController {

    @Resource
    private FlowerPileService flowerPileService;
    @Resource
    private OrderPileMapper orderPileMapper;
    @PostMapping("/findAllFlowerPile")
    public Result getAllFlowerPile(@RequestBody PortalVo portalVo){


        Result result = flowerPileService.getAllFlowerPile(portalVo);
        return result;
    }

    @PostMapping("/buyFlowerPile")
    public Result buyFlowerPile(@RequestBody OrderPile orderPile){
        int i = orderPileMapper.insertBySelf(orderPile);
        if (i>0){
            int id = orderPile.getF_id();
            int j = flowerPileService.updateAmountById(id);
            if (j>0){
                return Result.ok("购买成功");
            }
        }
        return Result.error("购买失败");
    }
}
