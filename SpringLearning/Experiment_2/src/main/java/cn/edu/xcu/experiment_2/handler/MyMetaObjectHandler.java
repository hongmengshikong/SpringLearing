package cn.edu.xcu.experiment_2.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(),LocalDateTime.class);
        this.strictInsertFill(metaObject, "createBy", () -> "admin", String.class);
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(),LocalDateTime.class);
        this.strictUpdateFill(metaObject, "updateBy", () -> "admin", String.class);
//        this.strictUpdateFill(metaObject,"delFlag",()->0,Integer.class);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(),
                LocalDateTime.class);
        this.strictUpdateFill(metaObject, "updateBy", () -> "admin", String.class);
    }
}

