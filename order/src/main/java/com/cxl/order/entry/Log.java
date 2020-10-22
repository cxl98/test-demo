package com.cxl.order.entry;

import lombok.Data;

import java.io.Serializable;
@Data
public class Log implements Serializable {
    private static final long serialVersionUID = 7672148872967466228L;

    private int id;
    private String logOp;//日志操作
    private String logType;//日志类型
    private long userId;//用户id
    private long createTime;//日志时间
    private String url;//url
}
