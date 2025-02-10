package com.nika.recruit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nika.recruit.model.entity.Notification;
import com.nika.recruit.model.vo.NotificationVO;

import java.util.List;

/**
 * @author ht
 * @description 针对表【notification(存储通知信息的表)】的数据库操作Service
 */
public interface NotificationService extends IService<Notification> {
    /**
     * 新增消息
     * @param notification
     * @return
     */
    boolean addNotification(Notification notification);

    /**
     * 批量新增
     * @param notifications
     * @return
     */
    boolean batchAddNotification(List<Notification> notifications);

    /**
     * 获取我的消息
     * @param userId
     * @return
     */
    NotificationVO getMyNotification(Long userId);

    /**
     * 将消息状态变为已读
     * @param notifications
     * @return
     */
    Boolean readNotifications(List<Notification> notifications);
}