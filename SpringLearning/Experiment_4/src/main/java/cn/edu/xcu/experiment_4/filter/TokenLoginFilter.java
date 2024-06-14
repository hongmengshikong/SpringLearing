package cn.edu.xcu.experiment_4.filter;


import cn.edu.xcu.experiment_4.entity.LoginUser;
import cn.edu.xcu.experiment_4.utils.R;
import cn.edu.xcu.experiment_4.utils.RedisCache;
import cn.edu.xcu.experiment_4.utils.ResponseUtil;
import cn.edu.xcu.experiment_4.utils.TokenManager;
import cn.edu.xcu.experiment_4.vo.UserLoginVo;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final RedisCache redisCache;
    private final TokenManager tokenManager;
    private final AuthenticationManager manager;

    public TokenLoginFilter( RedisCache redisCache, TokenManager tokenManager,AuthenticationManager manager) {
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login", "POST"));
        this.redisCache = redisCache;
        this.tokenManager = tokenManager;
        this.manager = manager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLoginVo user = null;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), UserLoginVo.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException("用户登录数据格式错误");
        }
        if (user == null) {
            throw new AuthenticationServiceException("用户登录数据格式错误");
        }
        if(StrUtil.isBlank(user.getVcode())){
            throw new AuthenticationServiceException("请携带验证码登录");
        }
        String key="vcode:"+request.getRemoteAddr()+":"+request.getRemotePort();
        String vcode= redisCache.getCacheObject(key);
//        System.out.println("vcode:"+vcode);
        if(!user.getVcode().equals(vcode)){
            throw new AuthenticationServiceException("验证码不正确");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        return manager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser) authResult.getPrincipal();
        String loginUserKey = UUID.randomUUID().toString();
        redisCache.setCacheObject("login:" + loginUserKey, loginUser, tokenManager.getExpiration() / 1000, TimeUnit.SECONDS);
        String token = tokenManager.createObjToken("login-user-key", loginUserKey, null);
        ResponseUtil.out(response, R.ok(token));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, R.fail(failed.getMessage()));
    }


}
