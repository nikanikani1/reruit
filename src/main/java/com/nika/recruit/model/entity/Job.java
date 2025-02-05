package com.nika.recruit.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.nika.recruit.model.dto.job.Requirements;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Job类表示一个职位信息。
 */
@Data
@TableName(value = "job",autoResultMap = true)
public class Job {
    /**
     * 职位ID。
     */
    private Long id;

    /**
     * 职位名称。
     */
    @NotNull(message = "职位必须要有标题")
    private String title;

    /**
     * 职位描述。
     */
    @NotNull(message = "职位必须要有描述")
    private String description;

    /**
     * 最低职位要求，用于粗筛，存储为JSON字符串。
     */
    @NotNull(message = "职位最低标准得定")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Requirements requirements;

    /**
     * 工作地点。
     */
    @NotNull(message = "请提供工作地点")
    private String location;

    /**
     * 薪资范围。
     */
    @NotNull(message = "请提供薪资范围")
    private String salaryRange;

    /**
     * 公司名称。
     */
    @NotNull(message = "请提供公司名称")
    private String companyName;

    /**
     * 额外福利，如有度假，五险一金等。
     */
    private String benefits;

    /**
     * 发布者ID，关联用户表。
     */
    private Long posterId;

    /**
     * 职位状态（例如：活跃、已关闭）。
     */
    private String status;

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