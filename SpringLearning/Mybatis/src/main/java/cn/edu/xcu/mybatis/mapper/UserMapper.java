package cn.edu.xcu.mybatis.mapper;

import cn.edu.xcu.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Mapper
    public User findUserById(Long id);
    public User findUserByUsername(String username);
    public User findUserByUsernameAndPassword(String username,String password);
    public User findUserByUsernameAndPassword2(@Param("username") String username, @Param("password") String password);
    public User findUserByMap(@Param("userMap") Map userMap);
    public List<User> findUserByEmail(User user);

    public User findUserWithIdCard(Long id);
    public User findUserWithIdCard2(Long id);

    public User findUserWithRoles(Long id);

    public User findUserWithRoles2(Long id);

    public List<User> findUsers(User user);

    public List<User> findUsersByChoose(User user);

    public int updateUser(User user);

    public int insertUser(User user);

    public int insertUsers(@Param("users") List<User> users);

    public int deleteUserByIds(@Param("ids")Long[] ids);

}
