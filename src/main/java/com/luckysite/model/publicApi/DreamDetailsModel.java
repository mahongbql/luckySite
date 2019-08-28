package com.luckysite.model.publicApi;

import lombok.Data;

import java.util.List;

/**
 * @author mahongbin
 * @date 2019/8/28 13:58
 * @Description
 */
@Data
public class DreamDetailsModel {

    private String id;

    private String title;

    private String des;

    private List<String> list;
}
