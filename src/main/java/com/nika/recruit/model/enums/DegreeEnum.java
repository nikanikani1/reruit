package com.nika.recruit.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ht
 */

public enum DegreeEnum {

    /**
     * 学历
     */
    College(0, "College"),
    BACHELOR(1, "BACHELOR"),
    MASTER(2, "MASTER"),
    PHD(3,"PHD");


    private final Integer sortValue;

    private final String name;

    DegreeEnum(Integer sortValue, String name) {
        this.sortValue = sortValue;
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

    public Integer getSortValue() {
        return sortValue;
    }

    public String getName() {
        return name;
    }
}
