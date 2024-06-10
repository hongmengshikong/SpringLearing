package cn.edu.xcu.springboot.security.filter;

import cn.edu.xcu.springboot.security.entity.LoginUser;
import cn.edu.xcu.springboot.security.utils.RedisCache;
import cn.edu.xcu.springboot.security.utils.TokenManager;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private RedisCache redisCache;
    private TokenManager manager;

    public JwtAuthenticationTokenFilter(RedisCache redisCache, TokenManager manager) {
        this.redisCache = redisCache;
        this.manager = manager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(authorization)) {
            String[] data = authorization.split(" ");
            if(data.length!=2){
                throw new ServletException("authorization格式不正确");
            }
            String token = data[1];
            String loginUserKey=null;
            try {
                loginUserKey= (String) manager.getObjectFromToken(token,"login-user-key");
            }catch (ExpiredJwtException e){
                throw new RuntimeException("token已过期");
            }catch (SignatureException e){
                throw new RuntimeException("无效token");
            }
            LoginUser loginUser= redisCache.getCacheObject("login:"+loginUserKey,LoginUser.class);
            if (ObjUtil.isNull(loginUser)){
                throw new RuntimeException("用户登录信息不存在");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        }else {
            filterChain.doFilter(request, response);
        }
    }
}
