package org.example.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pojo.FFloowers;

import java.util.List;

/**
 * ClassName: FlowerPileVo
 * Package: org.example.pojo.vo
 * Description:
 *
 * @Author
 * @Create 2024/6/26 19:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowerPileVo {
    private Integer id;
    private String name;
    private String price;
    private String amount;
    private String avatar;
    private List<?> flowers;
}
