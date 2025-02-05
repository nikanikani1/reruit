package com.nika.recruit.service.listener;

import com.nika.recruit.constants.NotificationConstant;
import com.nika.recruit.model.entity.Job;
import com.nika.recruit.model.entity.Notification;
import com.nika.recruit.model.entity.User;
import com.nika.recruit.service.JobService;
import com.nika.recruit.service.NotificationService;
import com.nika.recruit.service.UserService;
import com.nika.recruit.service.event.SaveResumeEvent;
import com.nika.recruit.service.event.UserRegisterEvent;
import com.nika.recruit.service.event.VoteJobEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监听各种事件来新增消息
 * @author ht
 */
@Component
@Slf4j
public class NotificationListener {

    @Resource
    private NotificationService notificationService;

    @Resource
    private JobService jobService;


    @Resource
    private UserService userService;


    private static final Long DEFAULT_SENDER_ID = 1L;


    /**
     * 简历保存后，给用户发通知
     * @param event
     */
    @Async("commonAsyncThreadPool")
    @EventListener(SaveResumeEvent.class)
    public void onSaveResumeEvent(SaveResumeEvent event){
        Long userId = event.getUserId();
        Notification notification = Notification.builder()
                .title(NotificationConstant.RESUME_FINISH_TITLE)
                .content(NotificationConstant.RESUME_FINISH_CONTENT)
                .senderId(DEFAULT_SENDER_ID)
                .receiverId(userId).build();
        boolean res = notificationService.addNotification(notification);
        if(!res){
            log.error("NotificationListener onSaveResumeEvent err");
        }
    }

    /**
     * 用户注册后提醒用户写简历
     * @param event
     */
    @Async("commonAsyncThreadPool")
    @EventListener(UserRegisterEvent.class)
    public void onUserRegisterEvent(UserRegisterEvent event){
        Long userId = event.getUserId();
        Notification notification = Notification.builder()
                .title(NotificationConstant.NOTIFY_RESUME_FINISH_TITLE)
                .content(NotificationConstant.NOTIFY_RESUME_FINISH_CONTENT)
                .senderId(DEFAULT_SENDER_ID)
                .receiverId(userId)
                .build();
        boolean res = notificationService.addNotification(notification);
        if(!res){
            log.error("NotificationListener onUserRegisterEvent err");
        }
    }

    /**
     * 用户投递后给招聘方发通知。
     * @param event
     */
    @Async("commonAsyncThreadPool")
    @EventListener(VoteJobEvent.class)
    public void onVoteJobEvent(VoteJobEvent event){
        Long userId = event.getUserId();
        Long jobId = event.getJobId();
        Job job = jobService.getById(jobId);
        User user = userService.getById(userId);
        Notification notification = Notification.builder()
                .title(String.format(NotificationConstant.JOB_VOTE_TITLE, user.getUserName(), job.getTitle()))
                .content(NotificationConstant.JOB_VOTE_CONTENT)
                .senderId(userId)
                .receiverId(job.getPosterId())
                .build();
        boolean res = notificationService.addNotification(notification);
        if(!res){
            log.error("NotificationListener onVoteJobEvent err");
        }
    }








}
