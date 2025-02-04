package com.nika.recruit.model.dto.resume;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 工作经历类，表示简历中的工作经历部分
 * @author ht
 */
@Data
public class Experience {
    /**
     * 公司名称
     */
    private String company;
    
    /**
     * 职位名称
     */
    private String position;
    
    /**
     * 工作开始日期
     */
    private Date startDate;
    
    /**
     * 工作结束日期
     */
    private Date endDate;
    
    /**
     * 工作职责和描述
     */
    private String description;
    
    /**
     * 工作成就列表
     */
    private List<String> achievements;
}