package com.nika.recruit.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.entity.Schedule;
import com.nika.recruit.model.enums.ScheduleStatusEnum;
import com.nika.recruit.model.enums.ScheduleTypeEnum;
import com.nika.recruit.service.ScheduleService;
import com.nika.recruit.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ht
* @description 针对表【schedule(日程表)】的数据库操作Service实现
* @createDate 2025-02-11 00:11:23
*/
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule>
    implements ScheduleService{

    @Resource
    private ScheduleMapper mapper;

    @Override
    public boolean add(Schedule schedule,Long userId) {

        verify(schedule);
        schedule.setUserId(userId);
        return mapper.insert(schedule) > 0;
    }

    private void verify(Schedule schedule){
        Date startTime = schedule.getStartTime();
        Date endTime = schedule.getEndTime();
        if(startTime.compareTo(endTime) > -1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"startDate should before endDate");
        }

        Integer scheduleType = schedule.getScheduleType();
        if(!ScheduleTypeEnum.getIntValues().contains(scheduleType)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"schedule type err");
        }

        Integer status = schedule.getStatus();
        if(!ScheduleStatusEnum.getIntValues().contains(status)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"schedule status err");
        }

    }

    @Override
    public boolean update(Schedule schedule) {
        verify(schedule);
        if(schedule.getId() == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return mapper.updateById(schedule) > 0;
    }

    @Override
    public Schedule getMy(Long userId) {
        return mapper.selectOne(new QueryWrapper<Schedule>().eq("userId",userId));
    }
}




