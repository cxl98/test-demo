package com.cxl.order.entry;

import lombok.Data;

import java.io.Serializable;
@Data
public class Log implements Serializable {
    private static final long serialVersionUID = 7672148872967466228L;


    private int id;
    private String logOp;
    private String logType;
    private long userId;
    private long createTime;
    private String url;
}
