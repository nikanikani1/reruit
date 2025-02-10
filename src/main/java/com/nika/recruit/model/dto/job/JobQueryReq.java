package com.nika.recruit.model.dto.job;

import com.nika.recruit.base.PageRequest;
import lombok.Data;

/**
 * @author ht
 */
@Data
public class JobQueryReq extends PageRequest {
    /**
     * 职位名称
     */
    String title;

    /**
     * 职位描述
     */
    String description;

    /**
     * 职位地点
     */
    String location;


    /**
     * 薪资范围
     */
    String salaryRange;
}
