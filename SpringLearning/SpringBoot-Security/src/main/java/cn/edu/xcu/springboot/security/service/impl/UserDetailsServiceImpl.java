package cn.edu.xcu.springboot.security.service.impl;

import cn.edu.xcu.springboot.security.entity.LoginUser;
import cn.edu.xcu.springboot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser= userService.getLoginUserByUsername(username);

        return loginUser;
    }
}
