package com.nika.recruit.model.dto.job;

import com.nika.recruit.model.dto.job.resumeStandard.EducationStandard;
import com.nika.recruit.model.dto.job.resumeStandard.SkillsStandard;
import lombok.Data;

/**
 * @author ht
 * 粗筛门槛
 */
@Data
public class Requirements {
    /**
     * 教育最低标准
     */
    private EducationStandard educationStandard;

    /**
     * 技能最低标准
     */
    private SkillsStandard skillStandard;
}
