package com.luckysite.entity;

import lombok.Data;

import javax.persistence.Id;

@Data
public class FunctionShow {

    @Id
    private Integer id;

    private String functionType;

    private Byte isShow;
}
