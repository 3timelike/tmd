package org.example.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.example.mapper.FFloowersMapper;
import org.example.mapper.FlowerPileFlowersMapper;
import org.example.mapper.FlowerPileMapper;
import org.example.pojo.FFloowers;
import org.example.pojo.FlowerPile;
import org.example.pojo.FlowerPileFlowers;
import org.example.pojo.vo.FlowerPileVo;
import org.example.pojo.vo.PortalVo;
import org.example.service.FFloowersService;
import org.example.service.FlowerPileService;
import org.example.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: FlowerPileServiceImpl
 * Package: org.example.service.serviceimpl
 * Description:
 *
 * @Author
 * @Create 2024/6/26 19:41
 * @Version 1.0
 */
@Service
public class FlowerPileServiceImpl implements FlowerPileService {

    @Resource
    private FlowerPileMapper flowerPileMapper;
    @Resource
    private FlowerPileFlowersMapper flowerPileFlowersMapper;
    @Resource
    private FFloowersMapper fFloowersMapper;

    @Override
    public Result getAllFlowerPile(PortalVo portalVo) {
        IPage page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        LambdaQueryWrapper<FlowerPile> lqw = new LambdaQueryWrapper<>();
        lqw.like(StringUtils.isNotEmpty(portalVo.getKeyWords()),FlowerPile::getName,portalVo.getKeyWords());
        List<FlowerPile> piles = flowerPileMapper.selectList(lqw);
        List<FlowerPileVo> list = new ArrayList<>();
        for (FlowerPile flowerPile : piles){
            FlowerPileVo flowerPileVo = new FlowerPileVo();
            flowerPile.setPrice(flowerPile.getPrice());
            List<FFloowers> floowers =  flowerPileFlowersMapper.selectbyFlowerId(flowerPile.getId());
            BeanUtils.copyProperties(flowerPile,flowerPileVo);
            flowerPileVo.setFlowers(floowers);
            list.add(flowerPileVo);
        }
        page.setRecords(list);
        page.setTotal(list.size());
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
    public int updateAmountById(int id) {
        int i = flowerPileMapper.updateAmount(id);
        return i;
    }

    @Override
    public int addNewFloower(FlowerPileVo flowerPileVo) {
        FlowerPile flowerPile = new FlowerPile();
        BeanUtils.copyProperties(flowerPileVo,flowerPile);
        flowerPileMapper.insertAndReturnKey(flowerPile);
        int key = flowerPile.getId();
        for (int i = 0; i < flowerPileVo.getFlowers().size(); i++) {
            FlowerPileFlowers flowerPileFlowers = new FlowerPileFlowers();
            flowerPileFlowers.setFlowerId((Integer) flowerPileVo.getFlowers().get(i));
            flowerPileFlowers.setFlowerPileId(key);
            flowerPileFlowersMapper.insert(flowerPileFlowers);
        }
        return 1;
    }

    @Override
    public int updateFloower(FlowerPileVo flowerPileVo) {
        FlowerPile flowerPile = new FlowerPile();
        BeanUtils.copyProperties(flowerPileVo,flowerPile);
        int res = flowerPileMapper.updateById(flowerPile);
        flowerPileFlowersMapper.deleteByFlowerPileId(flowerPile.getId());
        for (int i = 0; i < flowerPileVo.getFlowers().size(); i++) {
            FlowerPileFlowers flowerPileFlowers = new FlowerPileFlowers();
            flowerPileFlowers.setFlowerId((Integer) flowerPileVo.getFlowers().get(i));
            flowerPileFlowers.setFlowerPileId(flowerPile.getId());
            flowerPileFlowersMapper.insert(flowerPileFlowers);
        }
        return res;
    }

    @Override
    public int removeFloower(FlowerPileVo flowerPileVo) {
        int i = flowerPileMapper.deleteById(flowerPileVo.getId());
        flowerPileFlowersMapper.deleteByFlowerPileId(flowerPileVo.getId());
        return i;
    }

    @Override
    public Result findFloowersPilebyid(int id) {
        FlowerPile flowerPile = flowerPileMapper.selectByIdSelf(id);
        FlowerPileVo flowerPileVo = new FlowerPileVo();
        BeanUtils.copyProperties(flowerPile,flowerPileVo);

        List<Map<String,String>> floowers = new ArrayList<>();
        List<Integer> list = flowerPileFlowersMapper.selectListByFlowerPileId(id);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            Map<String, String> map = fFloowersMapper.selectByf_Id(list.get(i));
            System.out.println(map);
            floowers.add(map);
        }
        flowerPileVo.setFlowers(floowers);
        return Result.ok(flowerPileVo);
    }
}
