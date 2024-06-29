package org.example.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 告诉Mybatis-plus，这个类与数据库中的哪张表有关
@TableName(value = "f_floowers")

public class FFloowers  implements Serializable{
    @TableId(value = "f_id" )
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
    private String f_petal_num;
    @TableField("f_floower_hign")
    private String f_floower_hign;
    @TableField("f_create_date")
    private Date f_create_date;
    @TableField("f_num")
    private int f_num;
   @TableField("f_avatar")
   private String f_avatar;
    @TableLogic("is_deleted")

    private  Integer isDeleted;
}
