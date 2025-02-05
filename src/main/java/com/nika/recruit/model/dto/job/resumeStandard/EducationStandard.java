package com.nika.recruit.model.dto.job.resumeStandard;

import lombok.Data;

@Data
public class EducationStandard {

    /**
     * 平均绩点（GPA）
     */
    private double gpa;

    /**
     * 获得的学位
     */
    private String degree;

}
