package com.nika.recruit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nika.recruit.model.entity.Notification;

import java.util.List;

/**
 * @author ht
 * @description 针对表【notification(存储通知信息的表)】的数据库操作Service
 */
public interface NotificationService extends IService<Notification> {

    boolean addNotification(Notification notification);

    boolean batchAddNotification(List<Notification> notifications);

}