package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.FFloowers;
import org.example.pojo.FlowerPileFlowers;

import java.util.List;

/**
 * ClassName: FlowerPileFlowersMapper
 * Package: org.example.mapper
 * Description:
 *
 * @Author
 * @Create 2024/6/26 19:42
 * @Version 1.0
 */
@Mapper
public interface FlowerPileFlowersMapper extends BaseMapper<FlowerPileFlowers> {
    @Select("select * from flower_pile_flowers where flower_pile_id = #{id};")
    List<FFloowers> selectbyFlowerId(Integer id);
    @Delete("delete from flower_pile_flowers where flower_pile_id = #{id}")
    void deleteByFlowerPileId(Integer id);
    @Select("select flower_id from flower_pile_flowers where flower_pile_id = #{id}")
    List<Integer> selectListByFlowerPileId(int id);
}
