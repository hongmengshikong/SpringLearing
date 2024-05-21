package cn.edu.xcu.springboot.session.config;

import cn.edu.xcu.springboot.session.filter.FirstFilter;
import cn.edu.xcu.springboot.session.filter.SecondFilter;
import cn.edu.xcu.springboot.session.interceptor.FirstInterceptor;
import cn.edu.xcu.springboot.session.interceptor.LoginInterceptor;
import cn.edu.xcu.springboot.session.interceptor.SecondInterceptor;
import jakarta.servlet.FilterRegistration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.servlet.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    @Bean
//    public FilterRegistrationBean firstFilter(){
//        FilterRegistrationBean<Filter> register=new FilterRegistrationBean<>();
//        register.setFilter(new FirstFilter());
//        register.setUrlPatterns(Arrays.asList("/user/*"));
//        register.setOrder(2);
//        return register;
//    }
//    @Bean
//    public FilterRegistrationBean secondFilter(){
//        FilterRegistrationBean<Filter> register=new FilterRegistrationBean<>();
//        register.setFilter(new SecondFilter());
//        register.setUrlPatterns(Arrays.asList("/*"));
//        register.setOrder(1);
//        return register;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new FirstInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/");
//        registry.addInterceptor(new SecondInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/","/user/login","/img/**");
    }

}
