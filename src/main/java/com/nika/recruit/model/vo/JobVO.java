package com.nika.recruit.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.nika.recruit.model.dto.job.Requirements;
import com.nika.recruit.model.entity.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ht
 */
@Data
public class JobVO {

    /**
     * 职位ID。
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 该岗位被投递次数
     */
    private Integer voteCount;

    /**
     * 发布人
     */
    private User poster;

    /**
     * 职位名称。
     */
    private String title;

    /**
     * 职位描述。
     */
    private String description;

    /**
     * 最低职位要求，用于粗筛，存储为JSON字符串。
     */
    private Requirements requirements;

    /**
     * 工作地点。
     */
    private String location;

    /**
     * 薪资范围。
     */
    private String salaryRange;

    /**
     * 公司名称。
     */
    private String companyName;

    /**
     * 额外福利，如有度假，五险一金等。
     */
    private String benefits;

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
    private Integer isDelete;
}
