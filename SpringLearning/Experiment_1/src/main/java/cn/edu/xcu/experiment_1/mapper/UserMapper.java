package cn.edu.xcu.experiment_1.mapper;


import cn.edu.xcu.experiment_1.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    public User findUserById(Long id);

    public int saveUser(User user);

//    @Update("update t_user set username = #{username}, password = #{password}, userNick = #{userNick}, email = #{email}, age = #{age} where id = #{id}")
    public int updateUser(User user);

    public User findUserWithRoles(Long id);

    public int removeUserById(Long id);

    public List<User> findAllUsersWithCard();
}
