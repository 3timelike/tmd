package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 告诉Mybatis-plus，这个类与数据库中的哪张表有关
@TableName(value = "user")
public class User {
    @TableId(value = "uid")
    private String uid;
    @TableField(value = "username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("name")
    private String name;
    @TableField("age")
    private int age;
    @TableField("gender")
    private String gender;
    @TableField("phone_num")
    private String phoneNum;
    @TableField("email")
    private String email;
    @TableField("avatar")
    private String avatar="";
    @TableField("identify")
    private int identify;
    @TableLogic("is_deleted")
    private  int isDeleted;
}
