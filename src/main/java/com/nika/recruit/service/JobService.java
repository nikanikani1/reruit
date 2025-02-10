package com.nika.recruit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nika.recruit.base.PageResult;
import com.nika.recruit.model.dto.job.JobQueryReq;
import com.nika.recruit.model.entity.Job;
import com.nika.recruit.model.vo.JobVO;
import com.nika.recruit.model.vo.ResumeVO;

import java.util.List;

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

    /**
     * 获取所有创建的职位
     * @param userId
     * @return
     */
    List<Job> getMyJobs(Long userId);

    /**
     * 开启职位
     * @param id
     * @return
     */
    boolean openJob(Long id);

    /**
     * 查询职位
     * @param req
     * @return
     */
    PageResult<JobVO> pageJobs(JobQueryReq req);
}
