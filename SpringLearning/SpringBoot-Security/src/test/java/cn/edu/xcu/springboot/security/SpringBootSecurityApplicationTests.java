package cn.edu.xcu.springboot.security;

import cn.edu.xcu.springboot.security.entity.User;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.crypto.SecretKey;
import java.util.Date;

@SpringBootTest
class SpringBootSecurityApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        String encode=passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void createJwtToken() {
        String signKey = "Qww3bG076BRviMKYShat2T8LH217Mi1aBQmD3X16smHkhjWkceM3IesiXAFkO0MH";
        byte[] data = Decoders.BASE64.decode(signKey);
        SecretKey secretKey = Keys.hmacShaKeyFor(data);
        Date expire = new Date(System.currentTimeMillis() + 5 * 60 * 1000);
        User user=new User();
        user.setUserId(1l);
        user.setUserName("zhangsan");
        user.setPassword("xcu2024");
        user.setNickName("张三");
        String token = Jwts.builder()
                .signWith(secretKey)

                .claim("user", JSON.toJSONString(user))
//                .id("123")
//                .issuer("zhangsan")
//                .subject("xcu")
//                .claim("userNick", "张三")

                .expiration(expire)
                .compact();
        System.out.println(token);
    }

    @Test
    public void verifyToken() {
        String signKey = "Qww3bG076BRviMKYShat2T8LH217Mi1aBQmD3X16smHkhjWkceM3IesiXAFkO0MH";
        byte[] data = Decoders.BASE64.decode(signKey);
        SecretKey secretKey = Keys.hmacShaKeyFor(data);
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJ1c2VyIjoie1wibmlja05hbWVcIjpcIuW8oOS4iVwiLFwicGFzc3dvcmRcIjpcInhjdTIwMjRcIixcInVzZXJJZFwiOjEsXCJ1c2VyTmFtZVwiOlwiemhhbmdzYW5cIn0iLCJleHAiOjE3MTc5OTkwMzR9.7iNegy6jc8Km1cnVSH-tC0a0s47rudN2X6MaegsEMguqFV-TCpYg-A3SWqnuTeiY";
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

//        System.out.println(claims.getId());
//        System.out.println(claims.get("userNick"));
//        System.out.println(claims.getIssuer());
        User user=JSON.to(User.class,claims.get("user"));
        System.out.println(user);


    }
}
