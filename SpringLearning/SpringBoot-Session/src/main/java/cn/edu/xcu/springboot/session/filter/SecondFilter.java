package cn.edu.xcu.springboot.session.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
//@Component
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SecondFilter ... 初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURL().toString();
        log.info("SecondFilter ... doFilter,请求的url:" + url+"过滤器前置逻辑");
        if (url.indexOf("/user")>0){
            HttpServletResponse resp = (HttpServletResponse) response;
//            resp.sendRedirect("/");
//            return;
        }
        HttpServletResponse resp = (HttpServletResponse) response;
        // 调用过滤器链中的doFilter方法，对请求进行过滤
        chain.doFilter(request, response);
        log.info("SecondFilter ... doFilter,过滤器后置逻辑");
    }

    @Override
    public void destroy() {
        log.info("SecondFilter ... 销毁");
    }
}
