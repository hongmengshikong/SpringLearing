package cn.edu.xcu.springboot.mybatisplus;

import cn.edu.xcu.springboot.mybatisplus.entity.Role;
import cn.edu.xcu.springboot.mybatisplus.entity.User;
import cn.edu.xcu.springboot.mybatisplus.mapper.UserMapper;
import cn.edu.xcu.springboot.mybatisplus.service.RoleService;
import cn.edu.xcu.springboot.mybatisplus.service.UserService;
import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class SpringBootMybatisPlusApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUser(){
//        User user=userService.getById(1783730342293786631l);
//        System.out.println(user);
        List<User> users=userService.list();
        users.stream().forEach(System.out::println);
    }
    @Test
    public void saveUser(){
        User user=new User();
        user.setUsername("shikong");
        user.setUserNick("时空");
        user.setPassword("xcu2024");
        user.setAge(18);
        user.setBirth(LocalDateTime.now());
        boolean save = userService.save(user);
        System.out.println(save);

    }
    @Test
    public void delUser(){
        userService.removeById(1783730342293786631l);
    }
    @Test
    public void testUpdate(){
        User user=new User();
        user.setId(1783730342293786632l);
        user.setUsername("shikong111");
        user.setPassword("abc123");
        user.setVersion(0);
        userService.updateById(user);
    }
    @Test
    public void testPage(){
        Page<User> page = new Page<>(1, 5);
        userService.page(page);
        System.out.println(JSON.toJSONString(page));
    }
    @Test
    public void testWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id","username","user_nick","email","age","status")
                .eq("status",0)
                .and(u->u.like("email","xcu.edu.cn")
                        .or()
                        .eq("age",19))
                .orderByDesc("id");
        List<User> list = userService.list(wrapper);
        list.stream().forEach(System.out::println);
    }
    @Test
    public void testLambdaWrapper(){
        User user = new User();
        user.setStatus(0);
        user.setEmail("xcu.edu.cn");
        user.setAge(19);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.select(User::getId,User::getUsername,User::getUserNick,User::getEmail,User::getAge
                        ,User::getStatus)
                .eq(ObjUtil.isNotNull(user.getStatus()),User::getStatus,user.getStatus())
                .and(u->u.like(User::getEmail,user.getEmail())
                        .or()
                        .eq(User::getAge,user.getAge()))
                .orderByDesc(User::getId);
        List<User> list = userService.list(wrapper);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void findRole(){
//        User user=userService.getById(1783730342293786631l);
//        System.out.println(user);
        List<Role> roles=roleService.list();
        roles.stream().forEach(System.out::println);
    }
    @Test
    void testUpdateWrapper(){
        User user = new User();
        user.setId(37l);
        user.setUsername("lisi111");
        user.setUserNick("李四222");
        user.setEmail("lisi333@xcu.edu.cn");
        user.setAge(19);
        user.setPassword("123123");
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(user.getUsername()!=null,User::getUsername,user.getUsername())
                .set(user.getUserNick()!=null,User::getUserNick,user.getUserNick())
                .set(user.getEmail()!=null,User::getEmail,user.getEmail())
                .set(user.getAge()!=null,User::getAge,user.getAge())
                .set(user.getPassword()!=null,User::getPassword,user.getPassword())
                .eq(User::getId,user.getId());
        userService.update(wrapper);
    }
    @Test
    public void pageTest(){
        Page<User> page = new Page<>(1, 3);
        userService.page(page);
        System.out.println(JSON.toJSONString(page));
    }
    @Test
    public void pageTest2(){
        //查询ID⼤于等于100的⽤户列表分⻚
        Page<User> page = new Page<>(1, 3);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(User::getId,100l);
        userService.page(page,wrapper);
        System.out.println(JSON.toJSONString(page));
    }
    @Test
    public void pageTest3(){
        //查询ID⼤于等于100的⽤户列表分⻚
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPageVo(page,"张三");
        System.out.println(JSON.toJSONString(page));
    }
}
