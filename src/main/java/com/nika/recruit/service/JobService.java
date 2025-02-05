package com.nika.recruit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nika.recruit.model.entity.Job;

/**
* @author ht
*/
public interface JobService extends IService<Job> {
    /**
     * 发布职位
     * @param job
     * @param posterId
     * @return
     */
    boolean publishJob(Job job,Long posterId);


    /**
     * 发布职位
     * @param job
     * @return
     */
    boolean updateJob(Job job);


    /**
     * 关闭职位
     * @param jobId
     * @return
     */
    boolean closeJob(Long jobId);


}
