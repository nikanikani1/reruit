package com.nika.recruit.controller;

import com.nika.recruit.annotation.ParameterCheck;
import com.nika.recruit.base.BaseResponse;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.base.ResultUtils;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.entity.WorkTimeConfig;
import com.nika.recruit.service.UserService;
import com.nika.recruit.service.WorkTimeConfigService;
import com.nika.recruit.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 简历接口
 */
@RestController
@RequestMapping("/worktimeconf")
@Slf4j
public class WorkTimeConfigController {

    @Resource
    private WorkTimeConfigService workTimeConfigService;


    @Resource
    private UserService userService;


    /**
     * add
     * @return
     */
    @PostMapping("/add")
    @ParameterCheck
    public BaseResponse<Boolean> add(@RequestBody WorkTimeConfig config, HttpServletRequest request) {
        boolean result = workTimeConfigService.add(config,userService.getLoginUser(request).getId());
        ThrowUtils.throwIf(!result,new BusinessException(ErrorCode.SYSTEM_ERROR,"添加失败"));
        return ResultUtils.success(true);
    }




    /**
     * update worktimeconf
     * @return
     */
    @PostMapping("/update")
    @ParameterCheck
    public BaseResponse<Boolean> update(@RequestBody WorkTimeConfig config) {
        return ResultUtils.success(workTimeConfigService.update(config));
    }

    /**
     * update job
     * @return
     */
    @PostMapping("/my")
    @ParameterCheck
    public BaseResponse<WorkTimeConfig> getMyConf(HttpServletRequest request) {
        return ResultUtils.success(workTimeConfigService.getMyConf(userService.getLoginUser(request).getId()));
    }
}
