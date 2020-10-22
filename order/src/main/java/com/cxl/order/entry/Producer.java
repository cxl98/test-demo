package com.cxl.order.entry;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class Producer implements Serializable {
    private static final long serialVersionUID = -6365373226693641084L;
    private Long id;
    private String producerName;//商品名
    private String introduce;//商品介绍
    private int repertory;//库存
}
