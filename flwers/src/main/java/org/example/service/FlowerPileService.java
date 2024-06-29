package org.example.service;

import org.example.pojo.FlowerPile;
import org.example.pojo.vo.FlowerPileVo;
import org.example.pojo.vo.PortalVo;
import org.example.utils.Result;

/**
 * ClassName: FlowerPileService
 * Package: org.example.service
 * Description:
 *
 * @Author
 * @Create 2024/6/26 19:41
 * @Version 1.0
 */
public interface FlowerPileService {
    Result getAllFlowerPile(PortalVo portalVo);

    int updateAmountById(int id);

    int addNewFloower(FlowerPileVo flowerPile);

    int updateFloower(FlowerPileVo flowerPileVo);

    int removeFloower(FlowerPileVo flowerPileVo);

    Result findFloowersPilebyid(int parseInt);
}
