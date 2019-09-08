package com.luckysite.model.publicApi;

import lombok.Data;

import java.util.List;

/**
 * @author mahongbin
 * @date 2019/8/28 13:56
 * @Description
 */
@Data
public class DreamAnalyticalModel {

    private String reason;

    private List<DreamDetailsModel> result;

}
