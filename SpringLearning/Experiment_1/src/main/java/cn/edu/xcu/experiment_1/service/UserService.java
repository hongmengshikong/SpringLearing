package cn.edu.xcu.experiment_1.service;

import cn.edu.xcu.experiment_1.entity.User;

import java.util.List;

public interface UserService {
    public User findUserById(Long id);


    int saveUser(User user);

    int updateUser(User user);

    public User findUserWithRoleById(Long id);

    int removeUserById(Long id);

    public List<User> findAllUsersWithCard();
}
