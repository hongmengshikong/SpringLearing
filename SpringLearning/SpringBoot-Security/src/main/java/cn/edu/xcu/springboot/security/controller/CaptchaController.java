package cn.edu.xcu.springboot.security.controller;

import cn.edu.xcu.springboot.security.utils.R;
import cn.edu.xcu.springboot.security.utils.RedisCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
public class CaptchaController {

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/vcode")
    public R captcha(HttpServletRequest req) {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(160, 50, 4, 5);
        HashMap<String, String> map = new HashMap<>();
        String key = "vcode:" + req.getRemoteAddr() + ":" + req.getRemotePort();
        map.put("code", captcha.getImageBase64Data());
        redisCache.setCacheObject(key, captcha.getCode(),360, TimeUnit.SECONDS);
        return R.ok(map);
    }
}
