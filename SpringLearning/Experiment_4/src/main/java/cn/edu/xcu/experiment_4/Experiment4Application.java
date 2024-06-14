package cn.edu.xcu.experiment_4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.edu.xcu.experiment_4.mapper")
public class Experiment4Application {

    public static void main(String[] args) {
        SpringApplication.run(Experiment4Application.class, args);
    }

}
