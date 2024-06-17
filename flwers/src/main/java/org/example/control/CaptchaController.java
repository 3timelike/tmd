package org.example.control;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.config.RedisConfig;
import org.example.utils.RedisCache;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class CaptchaController {
    @Autowired
    private RedisCache redisUtil;


    @GetMapping("/captcha")
    public Result captcha() throws IOException, FontFormatException {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        // 设置内置字体
        specCaptcha.setFont(Captcha.FONT_1);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30分钟
        redisUtil.setCacheObject(key, verCode, 30, TimeUnit.MINUTES);
        // 将key和base64返回给前端
        // 如果不想要base64的头部data:image/png;base64,
        //specCaptcha.toBase64("");  // 加一个空的参数即可
        // png类型
//        SpecCaptcha captcha = new SpecCaptcha(130, 48);
//        captcha.text();  // 获取验证码的字符
//        captcha.textChar();  // 获取验证码的字符数组
//
//        // gif类型
//        GifCaptcha captcha = new GifCaptcha(130, 48);
//
//        // 中文类型
//        ChineseCaptcha captcha = new ChineseCaptcha(130, 48);
//
//        // 中文gif类型
//        ChineseGifCaptcha captcha = new ChineseGifCaptcha(130, 48);
//
//        // 算术类型
//        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
//        captcha.setLen(2);  // 几位数运算，默认是两位
//        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
//        captcha.text();  // 获取运算的结果：5
        Map<String,String> map = new HashMap<>();
        map.put("key", key);
        map.put("image", specCaptcha.toBase64());
        return Result.ok(map);
    }


}