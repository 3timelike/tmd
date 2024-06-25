package org.example.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class FVo {
    private int f_id;
    private String f_name;

    private String f_color;

    private String f_meaning;

    private String f_exist_time;

    private String f_product_area;

    private String f_aroma;

    private String f_petal_num;

    private String f_floower_hign;
    private  String f_avatar;
    private int f_num;
}
