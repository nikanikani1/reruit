package com.nika.recruit.controller;


import com.nika.recruit.annotation.AuthCheck;
import com.nika.recruit.annotation.ParameterCheck;
import com.nika.recruit.base.BaseResponse;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.base.ResultUtils;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.entity.Job;
import com.nika.recruit.model.entity.Vote;
import com.nika.recruit.model.vo.ResumeVO;
import com.nika.recruit.service.JobService;
import com.nika.recruit.service.UserService;
import com.nika.recruit.service.VoteService;
import com.nika.recruit.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 简历接口
 */
@RestController
@RequestMapping("/vote")
@Slf4j
public class VoteController {

    @Resource
    private VoteService voteService;


    @Resource
    private UserService userService;


    /**
     * publishJob
     * @return
     */
    @PostMapping("/job")
    @ParameterCheck
    @AuthCheck(mustRole = "jobseeker")
    public BaseResponse<Boolean> voteJob(@RequestBody Vote vote, HttpServletRequest request) {
        vote.setVoterId(userService.getLoginUser(request).getId());
        boolean result = voteService.voteJob(vote);
        ThrowUtils.throwIf(!result,new BusinessException(ErrorCode.SYSTEM_ERROR,"添加失败"));
        return ResultUtils.success(true);
    }

    /**
     * 根据职位id，查询投递者信息
     * @return
     */
    @GetMapping("/job/resumes")
    @ParameterCheck
    @AuthCheck(mustRole = "jobfinder")
    public BaseResponse<List<ResumeVO>> getResumesByJobId(@RequestParam("jobId") Long jobId) {
        return ResultUtils.success(voteService.getResumesByJobId(jobId));
    }



}
