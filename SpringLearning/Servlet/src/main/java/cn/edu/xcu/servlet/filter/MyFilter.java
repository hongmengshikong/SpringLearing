package cn.edu.xcu.servlet.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyFilter extends HttpFilter {


    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        System.out.println("MyFilter init");
    }
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain
            chain) throws IOException, ServletException {
        String uri = req.getRequestURI();
        System.out.println(uri);
        if (uri.indexOf("admin")>0){
            resp.setHeader("content-type","text/html;charset=utf-8");
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().println("当前⽤户不允许访问该链接");
            return;
        }
        chain.doFilter(req,resp);
    }
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("MyFilter destroy");
    }
}