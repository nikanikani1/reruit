package com.nika.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nika.recruit.mapper.NotificationMapper;
import com.nika.recruit.model.entity.Notification;
import com.nika.recruit.model.enums.IsReadEnum;
import com.nika.recruit.model.enums.NotificationTypeEnum;
import com.nika.recruit.model.vo.NotificationVO;
import com.nika.recruit.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public NotificationVO getMyNotification(Long userId) {
        List<Notification> notifications = notificationMapper.selectList(new QueryWrapper<Notification>().eq("receiverId", userId));
        // 使用 Map 按类型分组并同时统计未读数
        Map<String, List<Notification>> groupedNotifications = notifications.stream()
                .collect(Collectors.groupingBy(Notification::getType));

        // 获取系统通知及未读数
        List<Notification> systemNotifications = groupedNotifications.getOrDefault(
                NotificationTypeEnum.SYSTEM.getValue(),
                Collections.emptyList()
        );
        int sysCount = (int) systemNotifications.stream()
                .filter(n -> n.getIsRead().equals(IsReadEnum.UNREAD.getValue()))
                .count();

        // 获取招聘通知及未读数
        List<Notification> recruitmentNotifications = groupedNotifications.getOrDefault(
                NotificationTypeEnum.RECRUITMENT.getValue(),
                Collections.emptyList()
        );
        int recCount = (int) recruitmentNotifications.stream()
                .filter(n -> n.getIsRead().equals(IsReadEnum.UNREAD.getValue()))
                .count();
        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setRecruitmentNotifications(recruitmentNotifications);
        notificationVO.setSystemNotifications(systemNotifications);
        notificationVO.setRecruitmentUnReadCount(recCount);
        notificationVO.setSystemUnReadCount(sysCount);
        return notificationVO;
    }

    @Override
    public Boolean readNotifications(List<Notification> notifications) {
        List<Long> ids = notifications.stream().map(Notification::getId).collect(Collectors.toList());
        UpdateWrapper<Notification> wrapper = new UpdateWrapper<Notification>().in("id",ids).set("isRead",IsReadEnum.READ.getValue());
        return this.update(wrapper);
    }
}




