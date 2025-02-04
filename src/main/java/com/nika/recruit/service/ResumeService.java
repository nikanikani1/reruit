package com.nika.recruit.service;

import com.nika.recruit.model.entity.Resume;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ht
* @description 针对表【resume(简历表，存储用户的简历信息)】的数据库操作Service
*/
public interface ResumeService extends IService<Resume> {

    boolean add(Resume resume,Long userId);

    boolean update(Resume resume);

    boolean delete(Resume resume);

    Resume getMyResume(Long userId);
}
