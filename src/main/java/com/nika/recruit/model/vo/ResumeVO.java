package com.nika.recruit.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.nika.recruit.model.dto.resume.Education;
import com.nika.recruit.model.dto.resume.Experience;
import com.nika.recruit.model.dto.resume.Skills;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class ResumeVO {

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long resumeId;


    private UserVO userVO;

    /**
     * 关联的用户ID
     */
    private String realName;

    /**
     * 简历的摘要或简介
     */
    private String summary;

    /**
     * 工作经历列表
     */
    private List<Experience> experience;

    /**
     * 教育背景列表
     */

    private List<Education> education;


    private Skills skills;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
