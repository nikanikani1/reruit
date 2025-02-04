package com.nika.recruit.service.listener;

import com.nika.recruit.constants.NotificationConstant;
import com.nika.recruit.model.entity.Notification;
import com.nika.recruit.service.NotificationService;
import com.nika.recruit.service.event.SaveResumeEvent;
import com.nika.recruit.service.event.UserRegisterEvent;
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

    private static final Long DEFAULT_SENDER_ID = 1L;


    @Async("commonAsyncThreadPool")
    @EventListener(SaveResumeEvent.class)
    public void onSaveResumeEvent(SaveResumeEvent event){
        Long userId = event.getUserId();
        Notification notification = new Notification();
        notification.setTitle(NotificationConstant.RESUME_FINISH_TITLE);
        notification.setContent(NotificationConstant.RESUME_FINISH_CONTENT);
        notification.setSenderId(DEFAULT_SENDER_ID);
        notification.setReceiverId(userId);
        boolean res = notificationService.addNotification(notification);
        if(!res){
            log.error("NotificationListener onSaveResumeEvent err");
        }
    }

    @Async("commonAsyncThreadPool")
    @EventListener(UserRegisterEvent.class)
    public void onUserRegisterEvent(UserRegisterEvent event){
        Long userId = event.getUserId();
        Notification notification = new Notification();
        notification.setTitle(NotificationConstant.NOTIFY_RESUME_FINISH_TITLE);
        notification.setContent(NotificationConstant.NOTIFY_RESUME_FINISH_CONTENT);
        notification.setSenderId(DEFAULT_SENDER_ID);
        notification.setReceiverId(userId);
        notification.setReceiverId(userId);
        boolean res = notificationService.addNotification(notification);
        if(!res){
            log.error("NotificationListener onUserRegisterEvent err");
        }
    }





}
