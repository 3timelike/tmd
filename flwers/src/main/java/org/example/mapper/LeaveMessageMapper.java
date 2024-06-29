package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Message;
import org.example.pojo.vo.PortalVo;

import java.util.List;
import java.util.Map;

/**
 * ClassName: LeaveMessageMapper
 * Package: org.example.mapper
 * Description:
 *
 * @Author
 * @Create 2024/6/29 9:51
 * @Version 1.0
 */
@Mapper
public interface LeaveMessageMapper extends BaseMapper<Message> {
    @Select("SELECT id , u1.name,u1.avatar, content , send_date FROM message  m1 , `user` u1 WHERE u1.uid = m1.from and content like concat('%',#{portalVo.keyWords},'%')")
    IPage<Map> selectListSelf(IPage<Map> page,@Param("portalVo") PortalVo portalVo);
    @Insert("insert into message values(null,#{from},#{content},CURRENT_TIME);")
    int insertSelf(Message message);
}
