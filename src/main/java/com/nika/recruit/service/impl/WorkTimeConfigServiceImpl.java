package com.nika.recruit.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.entity.WorkTimeConfig;
import com.nika.recruit.service.WorkTimeConfigService;
import com.nika.recruit.mapper.WorkTimeConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ht
* @description 针对表【worktimeconfig(工作时间配置表)】的数据库操作Service实现
*/
@Service
public class WorkTimeConfigServiceImpl extends ServiceImpl<WorkTimeConfigMapper, WorkTimeConfig>
    implements WorkTimeConfigService {

    @Resource
    private WorkTimeConfigMapper mapper;

    @Override
    public boolean add(WorkTimeConfig config,Long userId) {
        verify(config);
        config.setUserId(userId);
        return mapper.insert(config) > 0;
    }

    private void verify(WorkTimeConfig config){
        Integer dayOfWeek = config.getDayOfWeek();
        Date startTime = config.getStartTime();
        Date endTime = config.getEndTime();

        if(startTime.compareTo(endTime) > -1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"startDate should before endDate");
        }

        if(dayOfWeek > 7){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"最高不超过7");
        }
    }


    @Override
    public boolean update(WorkTimeConfig config) {
        verify(config);
        if(config.getId() == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return mapper.updateById(config) > 0;
    }

    @Override
    public WorkTimeConfig getMyConf(Long userId) {
        return mapper.selectOne(new QueryWrapper<WorkTimeConfig>().eq("userId", userId));
    }
}




