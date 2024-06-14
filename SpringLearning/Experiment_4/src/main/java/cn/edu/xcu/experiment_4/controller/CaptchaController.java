package cn.edu.xcu.experiment_4.controller;


import cn.edu.xcu.experiment_4.utils.R;
import cn.edu.xcu.experiment_4.utils.RedisCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
