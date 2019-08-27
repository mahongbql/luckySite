package com.luckysite.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author mahongbin
 * @date 2019/8/27 11:01
 * @Description
 */
@Data
public class DreamAnalyticalDTO {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String des;

    @ApiModelProperty(value = "详细信息")
    private List<String> list;
}
