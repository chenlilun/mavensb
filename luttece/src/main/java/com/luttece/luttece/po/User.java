package com.luttece.luttece.po;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private String id;
    private String name;
    private Integer age;

}
