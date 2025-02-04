package com.nika.recruit.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author ht
 */
@Data
@TableName(value = "notification")
public class Notification {
    private Long id;
    private String title;
    private String content;
    private Long senderId;
    private Long receiverId;
    private Date createTime;
    private Integer isDelete;
}
