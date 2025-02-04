package com.nika.recruit.service.impl;
import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.dto.resume.Experience;
import com.nika.recruit.model.dto.resume.Education;
import com.nika.recruit.model.dto.resume.Skills;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nika.recruit.mapper.ResumeMapper;
import com.nika.recruit.model.entity.Resume;
import com.nika.recruit.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务实现
 * @author ht
 */
@Service
@Slf4j
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {


    @Resource
    private ResumeMapper resumeMapper;


    @Override
    public boolean add(Resume resume,Long userId) {
        verify(resume);
        Long count = resumeMapper.selectCount(new QueryWrapper<Resume>().eq("userId", userId));
        if(count > 0){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"每人仅能保存一份简历");
        }
        resume.setUserId(userId);
        return resumeMapper.insert(resume) > 0;
    }

    void verify(Resume resume){
        verifyExperience(resume.getExperience());
        verifyEducation(resume.getEducation());
        verifySkills(resume.getSkills());
    }

    private void verifyExperience(List<Experience> experiences){
        for (Experience experience : experiences) {
            String company = experience.getCompany();
            String position = experience.getPosition();
            String description = experience.getDescription();
            if(StringUtils.isAnyEmpty(company,position,description)){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"experience not legal");
            }
            Date startDate = experience.getStartDate();
            Date endDate = experience.getEndDate();
            if(startDate.compareTo(endDate) > -1){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"startDate should before endDate");
            }

            List<String> achievements = experience.getAchievements();
            if(CollectionUtil.isEmpty(achievements)){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"startDate should before endDate");
            }
        }
    }

    private void verifyEducation(List<Education> educations) {
        for (Education education : educations) {
            String institution = education.getInstitution();
            String degree = education.getDegree();

            if (StringUtils.isAnyEmpty(institution, degree)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "Education details are incomplete");
            }

            if (education.getGpa() < 0.0 || education.getGpa() > 4.0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "GPA must be between 0.0 and 4.0");
            }

            List<String> honors = education.getHonors();
            if (CollectionUtil.isEmpty(honors)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "Honors list cannot be empty");
            }
        }
    }

    private void verifySkills(Skills skills) {
        List<String> programmingLanguages = skills.getProgrammingLanguages();
        List<String> tools = skills.getTools();
        List<String> softSkills = skills.getSoftSkills();

        if (CollectionUtil.isEmpty(programmingLanguages) && CollectionUtil.isEmpty(tools) && CollectionUtil.isEmpty(softSkills)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Skills list cannot be all empty");
        }

        // 可以根据具体业务需求对每个列表进行更详细的验证，比如检查技能名称是否合理
    }

    @Override
    public boolean update(Resume resume) {
        verify(resume);
        if(resume.getResumeId() == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"resumeId is null");
        }
        return resumeMapper.updateById(resume) > 0;
    }

    @Override
    public boolean delete(Resume resume) {
        return resumeMapper.deleteById(resume) > 0;
    }

    @Override
    public Resume getMyResume(Long userId) {
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        return resumeMapper.selectOne(queryWrapper);
    }
}
