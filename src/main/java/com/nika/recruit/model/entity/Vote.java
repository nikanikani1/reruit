package com.nika.recruit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 简历投递关系表
 * @TableName vote
 */
@TableName(value ="vote")
@Data
public class Vote implements Serializable {
    /**
     * 自动生成的主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 发布者ID，关联用户表
     */
    @NotNull(message = "jobId非空")
    private Long jobId;

    /**
     * 求职者ID，关联用户表
     */
    private Long voterId;

    /**
     * 匹配分数0-100,由简历和职位描述由AI得出
     */
    private Integer matchScore;

    /**
     * AI根据职位描述，个性化的提出的建议
     */
    private String advice;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

}