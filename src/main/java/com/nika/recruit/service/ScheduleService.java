package com.nika.recruit.service;

import com.nika.recruit.model.entity.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ht
* @description 针对表【schedule(日程表)】的数据库操作Service
* @createDate 2025-02-11 00:11:23
*/
public interface ScheduleService extends IService<Schedule> {

    /**
     * 新增
     * @param schedule
     * @return
     */
    boolean add(Schedule schedule,Long userId);


    /**
     * 更新
     * @param schedule
     * @return
     */
    boolean update(Schedule schedule);


    /**
     * 获取我的
     * @param userId
     * @return
     */
    Schedule getMy(Long userId);
}
