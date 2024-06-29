package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FlowerPileFlowers
 * Package: org.example.pojo
 * Description:
 *
 * @Author
 * @Create 2024/6/26 19:15
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowerPileFlowers {
    private Integer id;
    private Integer flowerId;
    private Integer flowerPileId;
}
