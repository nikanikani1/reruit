package com.nika.recruit.controller;


import com.nika.recruit.annotation.AuthCheck;
import com.nika.recruit.annotation.ParameterCheck;
import com.nika.recruit.base.*;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.dto.job.JobQueryReq;
import com.nika.recruit.model.entity.Job;
import com.nika.recruit.model.entity.Resume;
import com.nika.recruit.model.vo.JobVO;
import com.nika.recruit.service.JobService;
import com.nika.recruit.service.ResumeService;
import com.nika.recruit.service.UserService;
import com.nika.recruit.service.event.SaveResumeEvent;
import com.nika.recruit.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 简历接口
 */
@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {

    @Resource
    private JobService jobService;


    @Resource
    private UserService userService;


    /**
     * publishJob
     * @return
     */
    @PostMapping("/publish")
    @ParameterCheck
    @AuthCheck(mustRole = "jobfinder")
    public BaseResponse<Boolean> publishJob(@RequestBody Job job, HttpServletRequest request) {
        boolean result = jobService.publishJob(job,userService.getLoginUser(request).getId());
        ThrowUtils.throwIf(!result,new BusinessException(ErrorCode.SYSTEM_ERROR,"添加失败"));
        return ResultUtils.success(true);
    }


    /**
     * close job
     * @return
     */
    @PostMapping("/close")
    @AuthCheck(mustRole = "jobfinder")
    public BaseResponse<Boolean> closeJob(@RequestBody Job job,HttpServletRequest request) {
        return ResultUtils.success(jobService.closeJob(job.getId()));
    }

    /**
     * close job
     * @return
     */
    @PostMapping("/open")
    @AuthCheck(mustRole = "jobfinder")
    public BaseResponse<Boolean> openJob(@RequestBody Job job) {
        return ResultUtils.success(jobService.openJob(job.getId()));
    }


    /**
     * update job
     * @return
     */
    @PostMapping("/update")
    @ParameterCheck
    @AuthCheck(mustRole = "jobfinder")
    public BaseResponse<Boolean> updateJob(@RequestBody Job job) {
        return ResultUtils.success(jobService.updateJob(job));
    }


    /**
     * update job
     * @return
     */
    @GetMapping("/my")
    @AuthCheck(mustRole = "jobfinder")
    public BaseResponse<List<Job>> getMyJobs(HttpServletRequest request) {
        Long userId = userService.getLoginUser(request).getId();
        return ResultUtils.success(jobService.getMyJobs(userId));
    }


    /**
     * update job
     * @return
     */
    @PostMapping("/page")
    @ParameterCheck
    public BaseResponse<PageResult<JobVO>> pageJobs(@RequestBody JobQueryReq req) {
        return ResultUtils.success(jobService.pageJobs(req));
    }




}
