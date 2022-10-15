package com.ruiji.controller;

import com.ruiji.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${ruiji.path}")
    private String basePath;

    @PostMapping("/upload")
    public R upload(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String type = originalFileName.substring(originalFileName.lastIndexOf("."));

        String FileName = UUID.randomUUID().toString();
        FileName = FileName+ type;

        //判断目录是否存在
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        file.transferTo(new File(basePath + FileName));
        return R.success(FileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            FileInputStream fis = new FileInputStream(new File(basePath + name));
            ServletOutputStream fos = response.getOutputStream();
            response.setContentType("image/jpeg");
            byte[] tmp = new byte[10];
            while (fis.read(tmp) != -1) {
                fos.write(tmp);
            }
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
