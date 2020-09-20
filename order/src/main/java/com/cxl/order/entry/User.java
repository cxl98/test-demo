package com.cxl.order.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Setter
@Getter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -6365373226693641083L;
    private long id;
    private String username;
    private String password;
}
