package com.luckysite.dto.publicApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author mahongbin
 * @date 2019/8/28 14:53
 * @Description
 */
@Data
public class DreamAnalyticalDetailsDTO {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String des;

    @ApiModelProperty(value = "详细信息")
    private List<String> list;

}
