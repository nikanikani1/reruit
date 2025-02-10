package com.nika.recruit.service.impl;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.constants.LockConstant;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.lock.LockTemplateSupport;
import com.nika.recruit.model.entity.Resume;
import com.nika.recruit.model.entity.User;
import com.nika.recruit.model.entity.Vote;
import com.nika.recruit.model.vo.ResumeVO;
import com.nika.recruit.model.vo.UserVO;
import com.nika.recruit.service.ResumeService;
import com.nika.recruit.service.UserService;
import com.nika.recruit.service.VoteService;
import com.nika.recruit.mapper.VoteMapper;
import com.nika.recruit.service.event.VoteJobEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ht
* @description 针对表【vote(简历投递关系表)】的数据库操作Service实现
*/
@Service
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote>
    implements VoteService{

    @Resource
    private VoteMapper voteMapper;

    @Resource
    private ApplicationEventPublisher publisher;


    @Resource
    private ResumeService resumeService;


    @Resource
    private UserService userService;



    @Resource
    private LockTemplateSupport lockTemplate;


    @Override
    public boolean voteJob(Vote vote) {
        Long jobId = vote.getJobId();
        Long voterId = vote.getVoterId();
        String lockKey = String.format(LockConstant.VOTE_JOB_LOCK,jobId,voterId);

        //防止出现不可重复读和幻读问题，这里需要加锁
        lockTemplate.lock(lockKey,1, TimeUnit.MINUTES, () -> {
            //1. 判断是否投递过
            Long count = voteMapper.selectCount(new QueryWrapper<Vote>()
                    .eq("jobId", jobId)
                    .eq("voterId", voterId));
            if(count > 0){
                throw new BusinessException(ErrorCode.OPERATION_ERROR,"禁止重复投递相同的岗位");
            }

            //2. 判断是否填写过简历
            long countResume = resumeService.count(new QueryWrapper<Resume>().eq("userId", voterId));
            if(countResume < 1){
                throw new BusinessException(ErrorCode.OPERATION_ERROR,"没有完善简历，无法投递");
            }

            //3. 执行插入
            if(voteMapper.insert(vote) < 0){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"插入失败");
            }

            //4. 发布事件
            publisher.publishEvent(new VoteJobEvent(voterId,jobId));
        });
       return true;
    }

    @Override
    public boolean updateVote(Vote vote) {
        if(vote.getId() == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"id 非空");
        }
        Integer matchScore = vote.getMatchScore();
        if(matchScore < 0 || matchScore > 100){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"matchScore 错误");
        }
        return this.updateById(vote);
    }

    @Override
    public List<ResumeVO> getResumesByJobId(Long jobId) {

        // 1. 查找出所有投递了该职位的用户id
        List<Long> voters = voteMapper.selectList(new QueryWrapper<Vote>().eq("jobId", jobId))
                .stream().map(Vote::getVoterId).collect(Collectors.toList());

        // 2. 查出该用户的所有简历信息
        List<Resume> resumes = resumeService.list(new QueryWrapper<Resume>().in("userId", voters));

        // 3. 查出所有的用户信息
        Map<Long, UserVO> users = userService.list(new QueryWrapper<User>().in("id", voters))
                .stream()
                .map(user -> userService.getLoginUserVO(user))
                .collect(Collectors.toMap(UserVO::getId, user -> user));

        // 4. 填充结果
        return resumes.stream().map(resume -> {
            ResumeVO resumeVO = new ResumeVO();
            BeanUtils.copyProperties(resume, resumeVO);
            resumeVO.setUserVO(users.get(resume.getUserId()));
            return resumeVO;
        }).collect(Collectors.toList());
    }

    @Override
    public Map<Long, Integer> getVoteCount(List<Long> jobIds) {
        if(CollectionUtil.isEmpty(jobIds)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"jobid 列表为空");
        }
        List<Vote> votes = voteMapper.selectList(new QueryWrapper<Vote>().in("jobId", jobIds));
        return votes.stream()
                .collect(Collectors.groupingBy(
                        Vote::getJobId,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
    }
}




