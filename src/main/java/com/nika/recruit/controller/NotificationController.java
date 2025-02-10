package com.nika.recruit.controller;


import com.nika.recruit.annotation.AuthCheck;
import com.nika.recruit.annotation.ParameterCheck;
import com.nika.recruit.base.BaseResponse;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.base.ResultUtils;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.dto.notification.NotificationReadReq;
import com.nika.recruit.model.entity.Job;
import com.nika.recruit.model.entity.Notification;
import com.nika.recruit.model.entity.Vote;
import com.nika.recruit.model.vo.NotificationVO;
import com.nika.recruit.model.vo.ResumeVO;
import com.nika.recruit.service.NotificationService;
import com.nika.recruit.service.UserService;
import com.nika.recruit.service.VoteService;
import com.nika.recruit.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


/**
 * 简历接口
 * @author ht
 */
@RestController
@RequestMapping("/notification")
@Slf4j
public class NotificationController {

    @Resource
    private NotificationService notificationService;


    @Resource
    private UserService userService;



    /**
     * 查询我的消息
     * @return
     */
    @GetMapping("/my")
    public BaseResponse<NotificationVO> getMyNotification(HttpServletRequest request) {
        Long userId = userService.getLoginUser(request).getId();
        return ResultUtils.success(notificationService.getMyNotification(userId));
    }


    /**
     * 查询我的消息
     * @return
     */
    @PostMapping("/read")
    @ParameterCheck
    public BaseResponse<Boolean> readNotification(@RequestBody NotificationReadReq notification) {
        List<Notification> notifications = Arrays.asList(Notification.builder().id(notification.getId()).build());
        Boolean res = notificationService.readNotifications(notifications);
        ThrowUtils.throwIf(!res,new BusinessException(ErrorCode.SYSTEM_ERROR));
        return ResultUtils.success(true);
    }


}
