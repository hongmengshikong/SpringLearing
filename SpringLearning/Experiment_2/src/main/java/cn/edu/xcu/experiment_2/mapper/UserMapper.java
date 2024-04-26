package cn.edu.xcu.experiment_2.mapper;

import cn.edu.xcu.experiment_2.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//@Mapper
public interface UserMapper extends BaseMapper<User> {
    public User findUserById_1(Long id);
}
