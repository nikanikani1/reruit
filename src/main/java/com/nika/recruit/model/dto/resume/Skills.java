package com.nika.recruit.model.dto.resume;

import lombok.Data;

import java.util.List;

/**
 * 技能类，表示简历中的技能部分
 * @author ht
 */
@Data
public class Skills {
    /**
     * 编程语言列表
     */
    private List<String> programmingLanguages;
    
    /**
     * 使用的工具和框架列表
     */
    private List<String> tools;
    
    /**
     * 软技能列表
     */
    private List<String> softSkills;
}