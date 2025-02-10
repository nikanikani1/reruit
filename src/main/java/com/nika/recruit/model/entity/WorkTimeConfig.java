package com.nika.recruit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 工作时间配置表
 * @TableName worktimeconfig
 */
@TableName(value ="worktimeconfig")
@Data
public class WorkTimeConfig implements Serializable {
    /**
     * 配置ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "userId")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 星期几：1-7
     */
    @TableField(value = "dayOfWeek")
    @NotNull
    private Integer dayOfWeek;

    /**
     * 开始时间
     */
    @TableField(value = "startTime")
    @NotNull
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField(value = "endTime")
    @NotNull
    private Date endTime;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

}