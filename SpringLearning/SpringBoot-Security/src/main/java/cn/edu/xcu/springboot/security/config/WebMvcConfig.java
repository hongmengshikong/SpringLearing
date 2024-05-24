package cn.edu.xcu.springboot.security.config;

import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

@Configuration
public class WebMvcConfig {
    @Bean
    public FastJsonHttpMessageConverter getConverter(){
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig jsonConfig = new FastJsonConfig();
        jsonConfig.setCharset(Charset.forName("UTF-8"));
        jsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(jsonConfig);
        converter.setDefaultCharset(Charset.forName("Utf-8"));
        return converter;
    }
}
