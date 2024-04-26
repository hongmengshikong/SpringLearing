package cn.edu.xcu.springboot.mybatis.mapper;

import cn.edu.xcu.springboot.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User findUserById(Long id);

}
