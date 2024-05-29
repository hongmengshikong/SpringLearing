package cn.edu.xcu.experiment_3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.edu.xcu.experiment_3.mapper")
public class Experiment3Application {

    public static void main(String[] args) {
        SpringApplication.run(Experiment3Application.class, args);
    }

}
