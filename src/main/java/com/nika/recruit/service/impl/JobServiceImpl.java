package com.nika.recruit.service.impl;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.base.PageResult;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.dto.job.JobQueryReq;
import com.nika.recruit.model.dto.job.resumeStandard.EducationStandard;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.nika.recruit.model.dto.job.Requirements;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nika.recruit.model.entity.Job;
import com.nika.recruit.model.entity.User;
import com.nika.recruit.model.enums.DegreeEnum;
import com.nika.recruit.model.enums.JobStatusEnum;
import com.nika.recruit.model.vo.JobVO;
import com.nika.recruit.service.JobService;
import com.nika.recruit.mapper.JobMapper;
import com.nika.recruit.service.UserService;
import com.nika.recruit.service.VoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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


    @Resource
    private UserService userService;


    @Resource
    private VoteService voteService;

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

    @Override
    public List<Job> getMyJobs(Long userId) {
        return jobMapper.selectList(new QueryWrapper<Job>().eq("posterId",userId));
    }

    @Override
    public boolean openJob(Long jobId) {
        Job job = jobMapper.selectById(jobId);
        if(job == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"job 已被删除，无法关闭");
        }
        UpdateWrapper<Job> wrapper = new UpdateWrapper<Job>()
                .eq("id",jobId)
                .set("status",JobStatusEnum.OPEN.getValue());
        return this.update(wrapper);
    }

    @Override
    public PageResult<JobVO> pageJobs(JobQueryReq req) {

        // 1.构造查询的queryWrapper
        QueryWrapper<Job> wrapper = getJobQueryWrapper(req);

        // 2. 执行分页查询
        Page<Job> page = this.page(new Page<>(req.getCurrent(), req.getPageSize()), wrapper);
        List<Job> jobs = page.getRecords();
        PageResult<JobVO> pageResult = new PageResult(page);

        if(CollectionUtil.isEmpty(jobs)){
            pageResult.setList(Collections.emptyList());
            return pageResult;
        }

        // 3, 并行获取投票数和用户信息
        List<Long> jobIds = jobs.stream().map(Job::getId).collect(Collectors.toList());
        List<Long> userIds = jobs.stream().map(Job::getPosterId).collect(Collectors.toList());

        CompletableFuture<Map<Long, Integer>> voteCountFuture = CompletableFuture.supplyAsync(() -> voteService.getVoteCount(jobIds));

        CompletableFuture<Map<Long, User>> usersFuture = CompletableFuture
                .supplyAsync(() -> userService.batchGetUserByIds(userIds)
                        .stream()
                        .collect(Collectors.toMap(User::getId, Function.identity())));

        CompletableFuture.allOf(voteCountFuture,usersFuture).join();

        Map<Long, Integer> voteCountMap = voteCountFuture.join();
        Map<Long,User> userMap = usersFuture.join();

        //4. 组装jobVO对象
        List<JobVO> res = jobs.stream().map(job -> {
            JobVO jobVO = new JobVO();
            BeanUtils.copyProperties(job, jobVO);
            jobVO.setPoster(userMap.getOrDefault(job.getPosterId(), null));
            jobVO.setVoteCount(voteCountMap.getOrDefault(job.getId(), 0));
            return jobVO;
        }).collect(Collectors.toList());
        pageResult.setList(res);

        return pageResult;
    }


    private QueryWrapper<Job> getJobQueryWrapper(JobQueryReq req){
        String title = req.getTitle();
        String description = req.getDescription();
        String location = req.getLocation();
        String salaryRange = req.getSalaryRange();

        QueryWrapper<Job> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotBlank(title)){
            wrapper.like("title",title);
        }
        if(StringUtils.isNotBlank(description)){
            wrapper.like("description",description);
        }
        if(StringUtils.isNotBlank(location)){
            wrapper.like("location",location);
        }
        if(StringUtils.isNotBlank(salaryRange)){
            wrapper.eq("salaryRange",salaryRange);
        }
        return wrapper;
    }
}




