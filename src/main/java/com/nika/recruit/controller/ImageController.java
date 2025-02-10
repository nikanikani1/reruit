package com.nika.recruit.controller;


import com.nika.recruit.annotation.AuthCheck;
import com.nika.recruit.annotation.ParameterCheck;
import com.nika.recruit.base.BaseResponse;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.base.ResultUtils;
import com.nika.recruit.exception.BusinessException;
import com.nika.recruit.model.entity.Job;
import com.nika.recruit.service.ImageService;
import com.nika.recruit.service.JobService;
import com.nika.recruit.service.UserService;
import com.nika.recruit.service.manager.ImageManager;
import com.nika.recruit.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;


/**
 * 简历接口
 */
@RestController
@RequestMapping("/image")
@Slf4j
public class ImageController {


    @Resource
    private ImageManager imageManager;


    /**
     * publishJob
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse<String> uploadImage(@RequestParam MultipartFile file) throws Exception{
        return ResultUtils.success(imageManager.upload(file));
    }





}
