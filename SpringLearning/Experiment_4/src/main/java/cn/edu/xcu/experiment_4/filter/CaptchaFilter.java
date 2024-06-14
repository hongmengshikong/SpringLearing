package cn.edu.xcu.experiment_4.filter;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
public class CaptchaFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        log.info("uri:"+uri);
        if (uri.indexOf("/user/login")<0){
            filterChain.doFilter(request,response);
            return;
        }
        String vcode = (String) session.getAttribute("vcode");
        String verifyCode = request.getParameter("vcode");
        if (StrUtil.isBlank(verifyCode)||!verifyCode.equals(vcode)){
            response.sendRedirect("/toLogin");
            return;
        }
        filterChain.doFilter(request,response);
    }
}
