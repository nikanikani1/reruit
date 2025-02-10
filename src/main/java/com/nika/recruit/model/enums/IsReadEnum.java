package com.nika.recruit.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ht
 */

public enum IsReadEnum {

    /**
     * 学历
     */
    UNREAD(0, "read"),
    READ(1, "unread");


    private final Integer value;

    private final String name;

    IsReadEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.name).collect(Collectors.toList());
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
