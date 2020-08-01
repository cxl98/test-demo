package com.cxl.count.entry;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Entry {
    private String model;//型号
    private float meters;//米数
    private float weight;//重量
    private float malevolent;//单价
    private int number;//数量
    private int machining;//加工
    private int data;//材料
    private int freight;//运费
    private float totalWeight;//总重量
    private float totalPrice;//总价
}
