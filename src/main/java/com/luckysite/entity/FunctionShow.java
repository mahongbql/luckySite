package com.luckysite.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class FunctionShow {

    @Id
    private Integer id;

    private String functionType;

    private Byte isShow;
}
