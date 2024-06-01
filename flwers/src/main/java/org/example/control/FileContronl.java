package org.example.control;


import cn.hutool.core.io.FileUtil;
import jakarta.annotation.Resource;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utils.Result;
import org.example.utils.UID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/files")
public class FileContronl {
    private static final String ip = "http://localhost";
    static String rootFilePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/";
    static String originalFilename = "";
    private String port = "9090";
    @Autowired
    private UserService userService;


    /**
     * 将上传的头像写入本地 rootFilePath
     */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        //获取文件名
        originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        //获取文件尾缀
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

        //重命名
        String uid = new UID().produceUID();
        originalFilename = uid + fileType;
        System.out.println(originalFilename);
        //存储位置
        String targetPath = rootFilePath + originalFilename;
        System.out.println(targetPath);
        //获取字节流
        FileUtil.writeBytes(file.getBytes(), targetPath);

        return Result.ok("上传成功");
    }

    /**
     * 将头像名称更新到数据库中
     */
    @PostMapping("/uploadAvatar")
    public Result<?> uploadStuAvatar(@RequestBody User student) {
        if (originalFilename != null) {
            student.setAvatar(originalFilename);
            System.out.println(student);
            int i = userService.updateNewStudent(student);
            if (i == 1) {
                return Result.ok(originalFilename);
            }
        } else {
            return Result.error("rootFilePath为空");
        }
        return Result.error("设置头像失败");
    }
    @GetMapping("/registavatar")
    public Result<?> registavatar() {
        if (originalFilename != null) {
            return Result.ok(originalFilename);
        } else {
            return Result.error("rootFilePath为空");
        }

    }
    @GetMapping("/initAvatar/{filename}")
    public Result<?> initAvatar(@PathVariable String filename) throws IOException {
        System.out.println(filename);
        String path = rootFilePath + filename;
        System.out.println(path);
        return Result.ok(getImage(path));
    }

    private Result<?> getImage(String path) throws IOException {

        //读取图片变成字节数组
        FileInputStream fileInputStream = new FileInputStream(path);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = fileInputStream.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        byte[] fileByte = bos.toByteArray();

        //进行base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
        //        String data = encoder.encode(fileByte);

        Base64.Encoder encoder = Base64.getEncoder();
        String data = encoder.encodeToString(fileByte);

        fileInputStream.close();
        bos.close();
        return Result.ok(data);
    }
}
