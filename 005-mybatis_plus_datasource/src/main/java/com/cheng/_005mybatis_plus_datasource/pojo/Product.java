package com.cheng._005mybatis_plus_datasource.pojo;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {

    private Integer id;
    private String name;
    private Integer price;
    private Integer version;
}
