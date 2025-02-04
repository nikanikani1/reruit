package com.nika.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nika.recruit.mapper.NotificationMapper;
import com.nika.recruit.model.entity.Notification;
import com.nika.recruit.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ht
* @description 针对表【notification(存储通知信息的表)】的数据库操作Service实现
*/
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
    implements NotificationService {

    @Resource
    private NotificationMapper notificationMapper;

    @Override
    public boolean addNotification(Notification notification) {
        return notificationMapper.insert(notification) > 0;
    }

    @Override
    public boolean batchAddNotification(List<Notification> notifications) {
        return this.saveBatch(notifications);
    }
}




