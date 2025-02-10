package com.nika.recruit.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ht
 * 日程类型
 */

public enum ScheduleTypeEnum {

    /**
     * 学历
     */
    FACE_TEST(0, "面试"),
    STUDY(1,"学习"),
    WORK(2,"工作"),
    OTHER(3,"其它");


    private final Integer val;

    private final String name;

    ScheduleTypeEnum(Integer val, String name) {
        this.val = val;
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
     * 获取int值列表
     *
     * @return
     */
    public static List<Integer> getIntValues() {
        return Arrays.stream(values()).map(item -> item.val).collect(Collectors.toList());
    }

    public Integer getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
}
