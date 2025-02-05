package com.nika.recruit.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public enum JobStatusEnum {

    OPEN("开启", "OPEN"),
    CLOSE("关闭", "CLOSE");

    private final String text;

    private final String value;

    JobStatusEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
