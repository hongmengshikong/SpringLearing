package cn.edu.xcu.springboot.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringBootSecurityApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        String encode=passwordEncoder.encode("123456");
        System.out.println(encode);
    }


}
