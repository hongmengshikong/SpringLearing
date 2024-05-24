package cn.edu.xcu.springboot.aop;

import cn.edu.xcu.springboot.aop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootAopApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void saveUserTest() {
        User user=new User();
        user.setId(1l);
        user.setUsername("zhangsan");
        user.setUserNick("张三");
        user.setPassword("123456");
        user.setAge(20);
        user.setEmail("zhangsan@qq.com");
//        redisTemplate.opsForValue().set("zhangsan",user);
        redisTemplate.opsForValue().set("zhangsan",user,600, TimeUnit.SECONDS);
    }
    @Test
    public void readUser(){
        User user=(User) redisTemplate.opsForValue().get("zhangsan");
        System.out.println(user);
    }
    @Test
    public void saveList(){
        ArrayList<User> users=new ArrayList<>();
        User user1=new User();
        user1.setId(1l);
        user1.setUsername("zhangsan");
        user1.setUserNick("张三");
        user1.setPassword("123456");
        user1.setAge(20);
        user1.setEmail("zhangsan@qq.com");
        users.add(user1);

        User user2=new User();
        user2.setId(2l);
        user2.setUsername("lisi");
        user2.setUserNick("李四");
        user2.setPassword("123456");
        user2.setAge(19);
        user2.setEmail("lisi@qq.com");
        users.add(user2);

        redisTemplate.opsForList().rightPushAll("users",users);
        redisTemplate.expire("users",10, TimeUnit.MINUTES);

    }

    @Test
    public void TestReadList(){
        //读取后删除
//        User user=(User) redisTemplate.opsForList().leftPop("users");
//        System.out.println(user);
        //读取后不删除
//        User user=(User) redisTemplate.opsForList().index("users",0);
//        System.out.println(user);
        List<User> users=redisTemplate.opsForList().range("users",0,1);
        System.out.println(users);
    }

}

