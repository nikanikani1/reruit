package com.nika.recruit.model.dto.resume;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 教育背景类，表示简历中的教育背景部分
 */
@Data
public class Education {
    /**
     * 学校或教育机构名称
     */
    private String institution;
    
    /**
     * 获得的学位
     */
    private String degree;
    
    /**
     * 教育开始日期
     */
    private Date startDate;
    
    /**
     * 教育结束日期
     */
    private Date endDate;
    
    /**
     * 平均绩点（GPA）
     */
    private double gpa;
    
    /**
     * 荣誉和奖项列表
     */
    private List<String> honors;
 
}