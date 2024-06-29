package org.example.control;

import jakarta.annotation.Resource;
import org.example.pojo.FlowerPileFlowers;
import org.example.service.FlowerPileService;
import org.example.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: FlowerInfoController
 * Package: org.example.control
 * Description:
 *
 * @Author
 * @Create 2024/6/28 20:21
 * @Version 1.0
 */
@RestController
public class FlowerInfoController {


    @Resource
    private FlowerPileService flowerPileService;
    @PostMapping("/findFloowerPileInfoByid")
    public Result findAllFloowers(@RequestBody String id){
        Result result = flowerPileService.findFloowersPilebyid(Integer.parseInt(id));
        return result;
    }
}
