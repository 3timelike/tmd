package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * ClassName: Message
 * Package: org.example.pojo
 * Description:
 *
 * @Author
 * @Create 2024/6/29 9:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @TableId(value = "id")
    private Integer id;
    private Integer from;
    private String content;
    private Date sendDate;
}
