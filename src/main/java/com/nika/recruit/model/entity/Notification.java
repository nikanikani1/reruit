package com.nika.recruit.model.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author ht
 */
@Data
@TableName(value = "notification",autoResultMap = true)
@Builder
public class Notification {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String title;
    private String content;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long senderId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long receiverId;
    private Date createTime;
    private String type;
    private Integer isRead;
    @TableLogic
    private Integer isDelete;
}
