package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FlowerPile
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
public class FlowerPile {

    private Integer id;
    private String name;
    private String price;
    private String amount;
    private String avatar;
}
