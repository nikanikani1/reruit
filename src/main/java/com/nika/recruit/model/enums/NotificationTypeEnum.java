package com.nika.recruit.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author ht
 */

public enum NotificationTypeEnum {
    /**
     * 消息类型
     */
    SYSTEM("系统消息", "system"),
    RECRUITMENT("招聘消息", "recruitment");

    private final String text;

    private final String value;

    NotificationTypeEnum(String text, String value) {
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
