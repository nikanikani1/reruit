package com.nika.recruit.model.vo;

import com.nika.recruit.model.entity.Notification;
import lombok.Data;

import java.util.List;

/**
 * @author ht
 */
@Data
public class NotificationVO {
    Integer systemUnReadCount;
    Integer recruitmentUnReadCount;
    List<Notification> systemNotifications;
    List<Notification> recruitmentNotifications;
}
