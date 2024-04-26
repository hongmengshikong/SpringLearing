package cn.edu.xcu.experiment_2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.edu.xcu.experiment_2.mapper")
public class Experiment2Application {

    public static void main(String[] args) {
        SpringApplication.run(Experiment2Application.class, args);
    }

}
