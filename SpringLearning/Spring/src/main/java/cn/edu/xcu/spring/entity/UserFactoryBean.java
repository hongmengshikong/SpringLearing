package cn.edu.xcu.spring.entity;

public class UserFactoryBean {
    public static User createUser(){
        return new User(4l,"zhaoliu","abcdef");
    }
}
