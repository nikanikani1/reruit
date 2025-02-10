package com.nika.recruit.service.manager;

import cn.hutool.core.lang.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.IOException;


/**
 * @author ht
 */
@Component
public class ImageManager {

    /**
     * 允许的图片类型
     */
    private static final String[] ALLOWED_EXTENSIONS = {".jpg", ".jpeg", ".png", ".gif"};

    @Value("${file.upload.dir}")
    private String uploadDir;

    @Value("${file.access.path:/images}")
    private String accessPath;

    @Value("${file.domain:http://localhost:8990/api}")
    private String domain;

    public String upload(MultipartFile file) throws IOException {
        // 验证文件是否为空
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空");
        }

        // 检查文件类型
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        originalFileName = originalFileName.toLowerCase();
        boolean isValidImage = false;
        for (String extension : ALLOWED_EXTENSIONS) {
            if (originalFileName.endsWith(extension)) {
                isValidImage = true;
                break;
            }
        }
        if (!isValidImage) {
            throw new IllegalArgumentException("只允许上传图片文件(jpg, jpeg, png, gif)");
        }

        // 创建目标目录
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            if (!uploadPath.mkdirs()) {
                throw new IOException("无法创建目录：" + uploadDir);
            }
        }

        // 生成新的文件名
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + fileExtension;

        // 构建目标文件路径
        File destFile = new File(uploadPath, newFileName);

        // 保存文件
        file.transferTo(destFile);

        // 返回可访问的URL
        return domain + accessPath + "/" + newFileName;
    }
}
