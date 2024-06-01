package org.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.*;
import org.example.pojo.OrderBack;
import org.example.pojo.OrderFloower;
import org.example.pojo.vo.PortalVo;

import java.util.Map;

/**
* @author zhang
* @description 针对表【order_floowers】的数据库操作Mapper
* @createDate 2024-05-24 20:27:16
* @Entity org.example.mapper.OrderFloowerspojo
*/
public interface OrderFloowersMapper extends BaseMapper<OrderFloower>{

    @Insert("INSERT INTO order_floowers (f_id,uid,o_phone_num, phone_num,o_area,o_note,o_time,f_name) VALUES(#{order.f_id},#{order.u_id},#{order.o_phone_num},#{order.phone_num},#{order.o_area},#{order.o_note},CURRENT_TIME,#{order.f_name})")
    int buyFloower(@Param("order") OrderFloower order);

    @Select("SELECT o_id,f_id,uid,o_phone_num,phone_num,o_area,o_note,o_time ,f_name FROM order_floowers where f_name like concat('%',#{portalVo.keyWords},'%')")
    IPage<Map>  selectAllOrder(IPage<Map> page,@Param("portalVo") PortalVo portalVo) ;

    @Update("UPDATE order_floowers  SET o_phone_num=#{o.o_phone_num},o_area = #{o.o_area},o_note = #{o.o_note}  WHERE o_id = #{o.o_id}")
    int updateOrderInfo(@Param("o") OrderFloower o);

    @Delete("DELETE FROM order_floowers WHERE o_id = #{o.o_id}")
    int removeOrder(@Param("o") OrderBack o);
}




