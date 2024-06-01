package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 告诉Mybatis-plus，这个类与数据库中的哪张表有关
@TableName(value = "order_floowers")
public class OrderFloower {
    @TableId(value = "o_id")
    private Integer o_id;
    @TableField(value = "f_id")
    private Integer f_id;
    @TableField("uid")
    private Integer u_id;
    @TableField("phone_num")
    private String phone_num;
    @TableField("o_phone_num")
    private String o_phone_num;
    @TableField("o_area")
    private String o_area;
    @TableField("o_note")
    private String o_note;
    @TableField("o_time")
    private Timestamp o_time;
    @TableField(value = "f_name")
    private String f_name;
    @TableLogic("is_deleted")
    private  int isDeleted;
}
