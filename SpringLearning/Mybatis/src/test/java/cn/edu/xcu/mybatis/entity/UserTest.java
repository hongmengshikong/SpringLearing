package cn.edu.xcu.mybatis.entity;

import cn.edu.xcu.mybatis.mapper.UserMapper;
import cn.edu.xcu.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class UserTest  {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testFindUser(){
        User user=userService.findUserById(1l);
        System.out.println(user);
    }
    @Test
    public void testFindUserByUsername(){
        User user = userMapper.findUserByUsername("hongmeng");
        System.out.println(user);
        User user2 = userMapper.findUserByUsername("hongmeng'#");
        System.out.println(user2);
    }
    @Test
    public void testFindUserByNameAndPass(){
        User user=userMapper.findUserByUsernameAndPassword("shikong","123456");
        System.out.println(user);
        User user2=userMapper.findUserByUsernameAndPassword("shikong'#","1234");
        System.out.println(user2);
    }
    @Test
    public void testFindUserByNameAndPass2(){
        User user=userMapper.findUserByUsernameAndPassword2("shikong","123456");
        System.out.println(user);
        User user2=userMapper.findUserByUsernameAndPassword2("shikong'#","1234");
        System.out.println(user2);
    }
    @Test
    public void testFindUserByMap(){
        Map<String,Object> userMap=new HashMap<>();
        userMap.put("username","shikong");
        userMap.put("password","123456");
        User user = userMapper.findUserByMap(userMap);
        System.out.println(user);
    }
    @Test
    public void testFindUserlist(){
        User user = new User();
        user.setEmail("zhangsan@qq.com");
        List<User> users = userMapper.findUserByEmail(user);
        users.forEach(System.out::println);
    }
    @Test
    public void testFindUserWithIdCard(){
        User user = userMapper.findUserWithIdCard(1l);
        System.out.println(user);
    }

    @Test
    public void testFindUserWithIdCard2(){
        User user = userMapper.findUserWithIdCard2(1l);
        System.out.println(user);
    }
    @Test
    public void testFindUserwithRoles(){
        User user = userMapper.findUserWithRoles(2l);
        System.out.println(user);
    }
    @Test
    public void testFindUserwithRoles2(){
        User user = userMapper.findUserWithRoles2(2l);
        System.out.println(user);
    }

    @Test
    public void testFindUserDynamic(){
        User user = new User();
        user.setUsername("zhang");
        user.setEmail("zhangsan@xcu.edu.cn");
        user.setAge(19);
// List<User> users = userMapper.findUsers(null);
        List<User> users = userMapper.findUsers(user);
        users.forEach(System.out::println);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(4l);
        user.setUsername("zhaosi");
        user.setAge(20);
        userMapper.updateUser(user);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setId(Long.valueOf("5"));
        user.setUsername("aaaaa");
        user.setPassword("111111");
        user.setEmail("aaaa@xcu.edu.cn");
        userMapper.insertUser(user);
        System.out.println(user);
    }

    @Test
    public void testInsertUsers(){
        User user = new User();
        user.setUsername("bbb");
        user.setPassword("222222");
        user.setEmail("bbb@xcu.edu.cn");
        user.setAge(19);
        User user2 = new User();
        user2.setUsername("ccc");
        user2.setPassword("333333");
        user2.setEmail("ccc@xcu.edu.cn");
        user2.setAge(18);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        userMapper.insertUsers(users);
    }
    @Test
    public void deleteUserBatch(){
        Long[] ids={6l,7l};
        userMapper.deleteUserByIds(ids);
    }
}