package com.cxl.count.entry;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Entry {
    private String model;//型号
    private int meters;//米数
    private int weight;//重量
    private int malevolent;//单价
    private int number;//数量
    private int machining;//加工
    private int data;//材料
    private int freight;//运费
}
