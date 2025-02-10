package com.nika.recruit.service;

import com.nika.recruit.model.entity.WorkTimeConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ht
* @description 针对表【worktimeconfig(工作时间配置表)】的数据库操作Service
*/
public interface WorkTimeConfigService extends IService<WorkTimeConfig> {

    /**
     * 新增
     * @param config
     * @return
     */
    boolean add(WorkTimeConfig config,Long userId);

    /**
     * 更新
     * @param config
     * @return
     */
    boolean update(WorkTimeConfig config);

    /**
     * 获取我的接口
     * @param userId
     * @return
     */
    WorkTimeConfig getMyConf(Long userId);
}
