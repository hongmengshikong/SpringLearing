package cn.edu.xcu.springboot.config;

import cn.edu.xcu.springboot.convert.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConvertConfig implements WebMvcConfigurer {
    @Autowired
    private DateConvert dateConvert;
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateConvert);
    }
}
