package com.luckysite.dto.publicApi;

import com.luckysite.dto.publicApi.DreamAnalyticalDetailsDTO;
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

    @ApiModelProperty(value = "解梦详情")
    List<DreamAnalyticalDetailsDTO> dreamAnalyticalDetails;
}
