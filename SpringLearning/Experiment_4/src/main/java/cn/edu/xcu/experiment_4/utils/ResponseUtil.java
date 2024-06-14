package cn.edu.xcu.experiment_4.utils;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ResponseUtil {
    public static void out(HttpServletResponse response, R r) {
        response.setStatus(HttpStatus.SUCCESS);
        response.setContentType("application/json;charset=utf-8");
        try {
            log.info("ResponseUtil返回数据：{}", JSON.toJSONString(r));
            response.getWriter().print(JSON.toJSONString(r));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
