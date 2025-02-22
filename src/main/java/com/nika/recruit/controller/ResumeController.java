package com.nika.recruit.controller;



import com.nika.recruit.annotation.AuthCheck;
import com.nika.recruit.annotation.ParameterCheck;
import com.nika.recruit.base.BaseResponse;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.base.ResultUtils;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.service.event.SaveResumeEvent;
import com.nika.recruit.utils.ThrowUtils;
import com.nika.recruit.model.entity.Resume;
import com.nika.recruit.service.ResumeService;
import com.nika.recruit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 简历接口
 */
@RestController
@RequestMapping("/resume")
@Slf4j
public class ResumeController {

    @Resource
    private ResumeService resumeService;

    @Resource
    private UserService userService;


    @Resource
    private ApplicationEventPublisher publisher;

    /**
     * add resume
     * @return
     */
    @PostMapping("/add")
    @ParameterCheck
    @AuthCheck(mustRole = "jobseeker")
    public BaseResponse<Boolean> addResume(@RequestBody Resume resume,HttpServletRequest request) {
        boolean result = resumeService.add(resume,userService.getLoginUser(request).getId());
        ThrowUtils.throwIf(!result,new BusinessException(ErrorCode.SYSTEM_ERROR,"添加失败"));
        publisher.publishEvent(new SaveResumeEvent(resume.getResumeId(), resume.getUserId()));
        return ResultUtils.success(true);
    }


    /**
     * add resume
     * @return
     */
    @GetMapping("/me")
    @ParameterCheck
    @AuthCheck(mustRole = "jobseeker")
    public BaseResponse<Resume> getMyResume(HttpServletRequest request) {
        Long id = userService.getLoginUser(request).getId();
        return ResultUtils.success(resumeService.getMyResume(id));
    }


    /**
     * update resume
     * @return
     */
    @PostMapping("/update")
    @ParameterCheck
    @AuthCheck(mustRole = "jobseeker")
    public BaseResponse<Boolean> updateResume(@RequestBody Resume resume,HttpServletRequest request) {
        boolean res = resumeService.update(resume);
        Long userId = userService.getLoginUser(request).getId();
        if(res){
            publisher.publishEvent(new SaveResumeEvent(resume.getResumeId(), userId));
        }
        return ResultUtils.success(res);
    }

}
