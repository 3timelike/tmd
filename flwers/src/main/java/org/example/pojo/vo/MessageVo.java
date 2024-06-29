package org.example.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pojo.User;

import java.sql.Date;

/**
 * ClassName: MessageVo
 * Package: org.example.pojo.vo
 * Description:
 *
 * @Author
 * @Create 2024/6/29 9:47
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVo {
    private Integer id;
    private User user;
    private Integer from;
    private String content;
    private Date sendDate;
}
