package com.nika.recruit.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ht
 */

public enum ScheduleStatusEnum {

    /**
     * 学历
     */
    ENABLE(1, "有效"),
    CANCEL(0, "取消");


    private final Integer value;

    private final String name;

    ScheduleStatusEnum(Integer value, String name) {
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

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getIntValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
