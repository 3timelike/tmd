package org.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.OrderBack;

/**
* @author zhang
* @description 针对表【order_back】的数据库操作Mapper
* @createDate 2024-05-25 14:25:24
* @Entity org.example.mapper.OrderBackpojo
*/
public interface OrderBackMapper extends BaseMapper<OrderBack> {

    @Insert("INSERT INTO order_back (o_id,f_id,uid,o_phone_num, phone_num,o_area,o_note,o_time,f_name,b_time) VALUES(#{order.o_id},#{order.f_id},#{order.uid},#{order.o_phone_num},#{order.phone_num},#{order.o_area},#{order.o_note},#{order.o_time},#{order.f_name},CURRENT_TIME)")
    int removeOrder(@Param("order") OrderBack order);
}




