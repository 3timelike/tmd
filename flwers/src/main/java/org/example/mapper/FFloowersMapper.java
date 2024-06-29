package org.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.*;
import org.example.pojo.FFloowers;
import org.example.pojo.vo.FVo;
import org.example.pojo.vo.PortalVo;

import java.util.List;
import java.util.Map;

/**
* @author zhang
* @description 针对表【f_floowers】的数据库操作Mapper
* @createDate 2024-05-23 19:27:46
* @Entity org.example.mapper.FFloowers
*/
@Mapper
public interface FFloowersMapper extends BaseMapper<FFloowers> {
    @Insert("INSERT INTO f_floowers (f_name,f_color,f_meaning,f_exist_time,f_product_area,f_aroma,f_petal_num,f_floower_hign,f_num,f_create_date,f_avatar) \n" +
            "VALUES(#{fVo.f_name},#{fVo.f_color},#{fVo.f_meaning},#{fVo.f_exist_time},#{fVo.f_product_area},#{fVo.f_aroma},#{fVo.f_petal_num},#{fVo.f_floower_hign},#{fVo.f_num},CURRENT_DATE,#{fVo.f_avatar}) ")
    int addNewFloower(@Param("fVo") FVo fVo);

    @Select("SELECT f_id, f_name,f_color,f_meaning,f_exist_time,f_product_area,f_aroma,f_petal_num,f_floower_hign,f_num,f_create_date,f_avatar FROM f_floowers WHERE is_deleted = 0 and f_name like concat('%',#{portalVo.keyWords},'%')")
    IPage<Map> findAllFloowers(IPage<Map> page,@Param("portalVo") PortalVo portalVo);

    @Update("UPDATE  f_floowers SET f_name=#{fVo.f_name},f_color = #{fVo.f_color},f_meaning = #{fVo.f_meaning},f_exist_time = #{fVo.f_exist_time},f_product_area=#{fVo.f_product_area},f_aroma=#{fVo.f_aroma},f_petal_num=#{fVo.f_petal_num},f_floower_hign=#{fVo.f_floower_hign} ,f_num=#{fVo.f_num} WHERE f_id = #{fVo.f_id}")
    int updatebyid(@Param("fVo")FVo fVo);

    @Delete("DELETE FROM f_floowers  WHERE f_id = #{fVo.f_id}")
    int removeFloower(@Param("fVo")FVo fVo);

    @Update("UPDATE  f_floowers SET f_num = f_num - 1 WHERE f_id = #{f_id}")
    int updateFnum(@Param("f_id") int f_id);

    @Update("UPDATE  f_floowers SET f_avatar = #{fVo.f_avatar} WHERE f_id = #{fVo.f_id}")
    int updateByf_Id(@Param("fVo") FFloowers fVo);

    @Select("SELECT f_id,f_name ,f_color,f_meaning,f_exist_time,f_product_area,f_aroma,f_petal_num,f_floower_hign,f_create_date,f_num,f_avatar FROM f_floowers WHERE is_deleted = 0 and f_id = #{id}")
    Map<String,String> selectByf_Id(@Param("id") int id);
}




