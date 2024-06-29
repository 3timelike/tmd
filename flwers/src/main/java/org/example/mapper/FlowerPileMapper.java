package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.FlowerPile;

/**
 * ClassName: FlowerPileMapper
 * Package: org.example.mapper
 * Description:
 *
 * @Author
 * @Create 2024/6/26 19:42
 * @Version 1.0
 */
@Mapper
public interface FlowerPileMapper extends BaseMapper<FlowerPile> {
    @Update("update flower_pile set amount = amount - 1 where id = #{id};")
    int updateAmount(int id);

    void insertAndReturnKey(FlowerPile flowerPile);
    @Select("select * from flower_pile where id = #{id}")
    FlowerPile selectByIdSelf(int id);
}
