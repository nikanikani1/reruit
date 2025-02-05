package com.nika.recruit.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.entity.Vote;
import com.nika.recruit.service.VoteService;
import com.nika.recruit.mapper.VoteMapper;
import com.nika.recruit.service.event.VoteJobEvent;
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


    @Override
    public boolean voteJob(Vote vote) {
        boolean res = voteMapper.insert(vote) > 0;
        Long jobId = vote.getJobId();
        Long voterId = vote.getVoterId();
        publisher.publishEvent(new VoteJobEvent(voterId,jobId));
        return res;
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
}




