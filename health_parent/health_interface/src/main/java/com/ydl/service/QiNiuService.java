package com.ydl.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 9:09
 * @Introduction:
 */
public interface QiNiuService {
    /**
     * 七牛云上传图片
     *
     * @param imgFile 图片信息
     */
    public String uploadImage(MultipartFile imgFile) throws IOException;

}
