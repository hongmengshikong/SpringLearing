package cn.edu.xcu.springboot.session.interceptor;

import cn.edu.xcu.springboot.session.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.*;
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断用户是否登录
        // 如果未登录，则返回错误信息或重定向到登录页面
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        if (user != null){
            if (uri.indexOf("/login")>0){
                response.sendRedirect("/");
                return false;
            }else {
                return true;
            }
        }else {
            response.sendRedirect("/login");
            return false;
        }
    }
}
