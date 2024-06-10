package cn.edu.xcu.springboot.security.handler;

import cn.edu.xcu.springboot.security.utils.R;
import cn.edu.xcu.springboot.security.utils.RedisCache;
import cn.edu.xcu.springboot.security.utils.ResponseUtil;
import cn.edu.xcu.springboot.security.utils.TokenManager;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.cell.CellSetter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;


public class TokenLogoutHandler implements LogoutHandler {

    private RedisCache redisCache;
    private TokenManager tokenManager;

    public TokenLogoutHandler(RedisCache redisCache,TokenManager tokenManager) {
        this.redisCache = redisCache;
        this.tokenManager = tokenManager;

    }
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String authorization = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(authorization)) {
            String[] data = authorization.split(" ");
            if(data.length!=2){
                ResponseUtil.out(response, R.fail("authorization格式不正确"));
                return;
            }
            String token = data[1];
            String loginUserKey=null;
            try {
                loginUserKey= (String) tokenManager.getObjectFromToken(token,"login-user-key");
                redisCache.deleteObject("login:"+loginUserKey);
                ResponseUtil.out(response, R.ok("用户注销成功"));
            }catch (ExpiredJwtException e){
                throw new RuntimeException("token已过期");
            }catch (SignatureException e){
                throw new RuntimeException("无效token");
            }
        }else {
            ResponseUtil.out(response,R.fail("当前用户没有登录,不需要注销"));
        }
    }

}
