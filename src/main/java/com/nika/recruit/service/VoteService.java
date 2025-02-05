package com.nika.recruit.service;

import com.nika.recruit.model.entity.Vote;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
