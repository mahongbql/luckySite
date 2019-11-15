package com.luckysite.common.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mahongbin
 * @date 2019/6/20 14:23
 * @Description
 */
@Getter
public enum WeekDaysEnum {
    /**
     * 日期枚举
     */

    sun(1, "周日"),
    Mon(2, "周一"),
    Tus(3, "周二"),
    Wen(4, "周三"),
    Thu(5, "周四"),
    Fri(6, "周五"),
    Sat(7, "周六");

    private int typeId;
    private String typeName;

    WeekDaysEnum(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public static List toList() {
        List list = new ArrayList();
        Map item = new HashMap();
        WeekDaysEnum[] values = values();

        for(int i = 0; i < values.length; ++i) {
            WeekDaysEnum value = values[i];
            item.put("typeId", value.getTypeId());
            item.put("typeName", value.getTypeName());
            list.add(item);
        }

        return list;
    }

    public static String getTypeNameById(int typeId) {
        String value = "未知" + typeId;
        WeekDaysEnum[] values = values();

        for(int i = 0; i < values.length; ++i) {
            WeekDaysEnum type = values[i];
            if (type.getTypeId() == typeId) {
                value = type.getTypeName();
                break;
            }
        }

        return value;
    }
}
