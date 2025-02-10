package com.nika.recruit.service;

import com.nika.recruit.model.entity.Job;
import com.nika.recruit.model.entity.Vote;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nika.recruit.model.vo.ResumeVO;

import java.util.List;
import java.util.Map;

/**
* @author ht
* @description 针对表【vote(简历投递关系表)】的数据库操作Service
*/
public interface VoteService extends IService<Vote> {

    /**
     * 投递某个岗位
     * @param vote
     * @return
     */
    boolean voteJob(Vote vote);

    /**
     * 更新此次投递
     * @param vote
     * @return
     */
    boolean updateVote(Vote vote);

    /**
     * 根据职位查找候选人简历信息
     * @param jobId
     * @return List<ResumeVO>
     */
    List<ResumeVO> getResumesByJobId(Long jobId);

    /**
     * 获取职位的投递次数
     * @param jobIds
     * @return
     */
    Map<Long,Integer> getVoteCount(List<Long> jobIds);
}
