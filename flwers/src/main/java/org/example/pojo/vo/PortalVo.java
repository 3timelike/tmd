package org.example.pojo.vo;

import lombok.Data;

@Data
public class PortalVo {
    private String keyWords ="";
    private int pageNum = 1;
    private int pageSize = 10;
}
