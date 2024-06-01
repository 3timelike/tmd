package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;


@Data
@AllArgsConstructor
@NoArgsConstructor
// 告诉Mybatis-plus，这个类与数据库中的哪张表有关
@TableName(value = "s_sign")
public class SSign{
    @TableId(value = "sign_id")
    private Integer sign_id;
    @TableField(value = "sign_username")
    private String username;
    @TableField(value = "sign_date")
    private Data data;
    @TableField(value = "sign_time")
    private Time time;
    @TableField(value = "sign_name")
    private String sign_name;
}
