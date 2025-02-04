package com.nika.recruit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.nika.recruit.model.dto.resume.Education;
import com.nika.recruit.model.dto.resume.Experience;
import com.nika.recruit.model.dto.resume.Skills;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@TableName(value = "resume",autoResultMap = true)
public class Resume {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long resumeId;

    /**
     * 关联的用户ID
     */
    private Long userId;


    /**
     * 关联的用户ID
     */
    @NotNull(message = "真实姓名非空")
    private String realName;

    /**
     * 简历的摘要或简介
     */
    @NotNull(message = "简介非空")
    private String summary;

    /**
     * 工作经历列表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @NotNull
    private List<Experience> experience;

    /**
     * 教育背景列表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @NotNull
    private List<Education> education;

    /**
     * 技能信息
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @NotNull
    private Skills skills;
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