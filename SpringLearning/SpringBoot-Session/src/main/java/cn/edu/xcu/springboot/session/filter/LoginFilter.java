package cn.edu.xcu.springboot.session.filter;

import cn.hutool.core.util.ArrayUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
//@Component
public class LoginFilter extends HttpFilter {
    //定义了不需要过滤的静态资源路径列表。这些路径中的请求将直接放行，不会进行过滤。
    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
            "/static/", "/resources/", "/public/", "/webjars/", "/images/", "/css/", "/js/", "/img/"
    );

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {

        // 获取请求的URI
        String uri = req.getRequestURI();

        // Exclude static resources from being filtered
        // 遍历不需要过滤的静态资源路径列表，检查当前请求的URI是否以这些路径开头。如果是，则直接放行，不进行过滤。
        for (String excludedPath : EXCLUDED_PATHS) {
            if (uri.startsWith(excludedPath)) {
                chain.doFilter(req, resp);
                return;
            }
        }

        // 检查用户是否已登录，如果已登录，则直接放行，否则重定向到登录页面。
        Cookie[] cookies = req.getCookies();
        if (ArrayUtil.isNotEmpty(cookies)){
            for (Cookie c : cookies){
                if(c.getName().equals("username")){
                    log.info("当前用户已登录");
                    if (uri.indexOf("login")>0){
                        resp.sendRedirect("/");
                    }else {
                        chain.doFilter(req,resp);
                    }
                }
            }
        }

        if(uri.indexOf("login")>0) {
            chain.doFilter(req, resp);
        }
        try {
            resp.sendRedirect("/login");
        } catch (IllegalStateException e) {
            // 处理异常，例如记录日志或返回错误页面
        }
    }
}

