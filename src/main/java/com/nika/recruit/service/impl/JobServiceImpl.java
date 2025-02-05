package com.nika.recruit.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.dto.job.resumeStandard.EducationStandard;
import java.util.List;

import com.nika.recruit.model.dto.job.Requirements;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nika.recruit.model.entity.Job;
import com.nika.recruit.model.enums.DegreeEnum;
import com.nika.recruit.model.enums.JobStatusEnum;
import com.nika.recruit.service.JobService;
import com.nika.recruit.mapper.JobMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ht
*/
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job>
    implements JobService{

    @Resource
    private JobMapper jobMapper;

    @Override
    public boolean publishJob(Job job, Long posterId) {
        verifyJob(job);
        job.setPosterId(posterId);
        job.setStatus(JobStatusEnum.OPEN.getValue());
        return jobMapper.insert(job) > 0;
    }

    @Override
    public boolean updateJob(Job job) {
        verifyJob(job);
        if(job.getId() == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"ID not null");
        }
        return this.updateById(job);
    }

    private void verifyJob(Job job) {

        Requirements requirements = job.getRequirements();

        EducationStandard educationStandard = requirements.getEducationStandard();
        double gpa = educationStandard.getGpa();
        if(gpa < 0.0 || gpa > 4.0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"gpa 错误");
        }

        List<String> degrees = DegreeEnum.getValues();
        if(!degrees.contains(educationStandard.getDegree())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"degree 不合法");
        }
    }

    @Override
    public boolean closeJob(Long jobId) {
        Job job = jobMapper.selectById(jobId);
        if(job == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"job 已被删除，无法关闭");
        }
        UpdateWrapper<Job> wrapper = new UpdateWrapper<Job>()
                .eq("id",jobId)
                .set("status",JobStatusEnum.CLOSE.getValue());
        return this.update(wrapper);
    }
}




