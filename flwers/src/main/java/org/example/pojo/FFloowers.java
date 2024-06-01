package org.example.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 告诉Mybatis-plus，这个类与数据库中的哪张表有关
@TableName(value = "f_floowers")

public class FFloowers {
    @TableId(value = "f_id")
    private Integer f_id;
    @TableField(value = "f_name")
    private String f_name;
    @TableField("f_color")
    private String f_color;
    @TableField("f_meaning")
    private String f_meaning;
    @TableField("f_exist_time")
    private String f_exist_time;
    @TableField("f_product_area")
    private String f_product_area;
    @TableField("f_aroma")
    private String f_aroma;
    @TableField("f_petal_num")
    private int f_petal_num;
    @TableField("f_floower_hign")
    private int f_floower_hign;
    @TableField("f_create_date")
    private Data f_create_date;
    @TableField("f_num")
    private int f_num;

    @TableLogic("is_deleted")
    private  int isDeleted;
}
