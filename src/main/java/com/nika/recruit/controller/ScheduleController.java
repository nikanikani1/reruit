package com.nika.recruit.controller;

import com.nika.recruit.annotation.ParameterCheck;
import com.nika.recruit.base.BaseResponse;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.base.ResultUtils;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.entity.Schedule;
import com.nika.recruit.model.entity.WorkTimeConfig;
import com.nika.recruit.service.ScheduleService;
import com.nika.recruit.service.UserService;
import com.nika.recruit.service.WorkTimeConfigService;
import com.nika.recruit.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 简历接口
 */
@RestController
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {

    @Resource
    private ScheduleService scheduleService;


    @Resource
    private UserService userService;

    /**
     * add
     * @return
     */
    @PostMapping("/add")
    @ParameterCheck
    public BaseResponse<Boolean> add(@RequestBody Schedule schedule, HttpServletRequest request) {
        boolean result = scheduleService.add(schedule,userService.getLoginUser(request).getId());
        ThrowUtils.throwIf(!result,new BusinessException(ErrorCode.SYSTEM_ERROR,"添加失败"));
        return ResultUtils.success(true);
    }




    /**
     * update worktimeconf
     * @return
     */
    @PostMapping("/update")
    @ParameterCheck
    public BaseResponse<Boolean> update(@RequestBody Schedule schedule) {
        return ResultUtils.success(scheduleService.update(schedule));
    }

    /**
     * update job
     * @return
     */
    @PostMapping("/my")
    @ParameterCheck
    public BaseResponse<Schedule> getMySchedule(HttpServletRequest request) {
        return ResultUtils.success(scheduleService.getMy(userService.getLoginUser(request).getId()));
    }
}
