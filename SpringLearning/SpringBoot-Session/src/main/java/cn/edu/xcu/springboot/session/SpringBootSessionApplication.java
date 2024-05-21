package cn.edu.xcu.springboot.session;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.edu.xcu.springboot.session.mapper")
public class SpringBootSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSessionApplication.class, args);
    }

}
