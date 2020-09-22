package com.cxl.order.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Setter
@Getter
@ToString
public class Producer implements Serializable {
    private static final long serialVersionUID = -6365373226693641084L;
    private Long id;
    private String producerName;
    private String introduce;
}
