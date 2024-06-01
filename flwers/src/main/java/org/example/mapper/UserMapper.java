package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.User;
import org.example.pojo.vo.PortalVo;

import java.util.Map;

@Mapper
public  interface UserMapper extends BaseMapper<User> {


    IPage<Map> selectAllUser(IPage<Map> page, @Param("portalVo") PortalVo portalVo);
}
