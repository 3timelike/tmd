package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Message;

import java.util.List;

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
    @Select("select * from message")
    List<Message> selectListSelf();
    @Insert("insert into message values(null,#{from},#{content},CURRENT_TIME);")
    int insertSelf(Message message);
}
