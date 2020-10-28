package com.cxl.order.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Setter
@Getter
@ToString
public class Order implements Serializable {
    private static final long serialVersionUID = -6365373226693641085L;
    private Long id;//订单id
    private String orderSn;//订单编号
    private Date createTime;//提交时间
    private String username;//用户账号
    private BigDecimal totalAmount;//订单总额
    private BigDecimal payAmount;//应付金额
    private Long pName;//商品名
    private int number;//购买数量
    private Integer status;//订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单

}
