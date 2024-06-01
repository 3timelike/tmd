package org.example.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.SSign;
import org.example.pojo.vo.PortalVo;

import java.util.ArrayList;
import java.util.Map;

/**
* @author zhang
* @description 针对表【s_sign】的数据库操作Mapper
* @createDate 2024-05-21 13:29:20
* @Entity org.example.mapper.SSign
*/
public interface SSignMapper extends BaseMapper<SSign> {
    @Insert("INSERT INTO s_sign  (sign_username,sign_date, sign_time,sign_name) VALUES(#{username},CURRENT_DATE(), CURRENT_TIME(),#{name})")
    int signup(@Param("username")String username,@Param("name")String name);

    @Select("select * from s_sign where sign_username = (#{username}) and sign_date = CURRENT_DATE() ")
    ArrayList<SSign> selectAll(@Param("username")String username);

    @Select("select  sign_username,sign_name,sign_date,sign_time from s_sign where sign_date = CURRENT_DATE() and sign_name like concat('%',#{portalVo.keyWords},'%')")
    IPage<Map> selectTodayUser(IPage<Map> page,@Param("portalVo") PortalVo portalVo);

    @Select("SELECT  u.username,u.name FROM USER u  WHERE u.identify = 0 AND u.username NOT IN (SELECT sign_username FROM s_sign WHERE sign_date = CURRENT_DATE) and u.name like concat('%',#{portalVo.keyWords},'%')")
    IPage<Map> selectTodayNoUser(IPage<Map> page,@Param("portalVo") PortalVo portalVo);
}




