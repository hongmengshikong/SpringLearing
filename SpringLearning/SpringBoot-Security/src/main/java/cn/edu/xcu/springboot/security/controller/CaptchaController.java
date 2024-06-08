package cn.edu.xcu.springboot.security.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CaptchaController {

//    @Resource(name = "captchaProducer")
//    private Producer captchaProducer;
//    @GetMapping("/vcode")
//    public void captcha(HttpSession session, HttpServletResponse response)throws IOException {
//        String capStr = captchaProducer.createText();
//        session.setAttribute("vcode",capStr);
//        BufferedImage image = captchaProducer.createImage(capStr);
//        response.setContentType("image/jpeg");
//        ImageIO.write(image,"jpg",response.getOutputStream());
//    }

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;
    @GetMapping("/vcode")
    public void captcha(HttpSession session, HttpServletResponse response)throws IOException {
        String capText = captchaProducerMath.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        session.setAttribute("vcode",code);
        BufferedImage image = captchaProducerMath.createImage(capStr);
        response.setContentType("image/jpeg");
        ImageIO.write(image,"jpg",response.getOutputStream());
    }
//    @GetMapping("/vcode")
//    public void captcha(HttpSession session, HttpServletResponse response) throws IOException {
//        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(160, 50, 4, 5);
//        String code = captcha.getCode();
//        session.setAttribute("vcode", code);
//        captcha.write(response.getOutputStream());
//    }
}
