package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.*;
import org.example.pojo.OrderBack;
import org.example.pojo.OrderFloower;
import org.example.pojo.OrderPile;
import org.example.pojo.vo.PortalVo;

import java.util.Map;

/**
 * ClassName: OrderPileMapper
 * Package: org.example.mapper
 * Description:
 *
 * @Author
 * @Create 2024/6/26 21:09
 * @Version 1.0
 */
@Mapper
public interface OrderPileMapper extends BaseMapper<OrderPile> {
    @Update("")
    int updateAmountById(int id);
    @Select("SELECT o_id,f_id,uid,o_phone_num,phone_num,o_area,o_note,o_time ,f_name FROM order_pile where uid = #{uid} and f_name like concat('%',#{portalVo.keyWords},'%')")
    IPage<Map> selectUserOrders(IPage<Map> page, PortalVo portalVo, Integer uid);
    @Insert("INSERT INTO order_pile (f_id,uid,o_phone_num, phone_num,o_area,o_note,o_time,f_name) VALUES(#{order.f_id},#{order.u_id},#{order.o_phone_num},#{order.phone_num},#{order.o_area},#{order.o_note},CURRENT_TIME,#{order.f_name})")
    int insertBySelf(@Param("order") OrderPile order);


    @Delete("DELETE FROM order_pile WHERE o_id = #{o.o_id}")
    int removeOrder(@Param("o") OrderBack o);

    @Update("UPDATE order_pile  SET o_phone_num=#{o.o_phone_num},o_area = #{o.o_area},o_note = #{o.o_note}  WHERE o_id = #{o.o_id}")
    int updateOrderInfo(@Param("o") OrderPile o);

    @Select("SELECT o_id,f_id,uid,o_phone_num,phone_num,o_area,o_note,o_time ,f_name FROM order_pile where f_name like concat('%',#{portalVo.keyWords},'%')")
    IPage<Map>  selectAllOrder(IPage<Map> page,@Param("portalVo") PortalVo portalVo) ;
}
