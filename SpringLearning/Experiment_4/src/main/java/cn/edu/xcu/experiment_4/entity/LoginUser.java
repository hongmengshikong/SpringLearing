package cn.edu.xcu.experiment_4.entity;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class LoginUser implements UserDetails {

    private User user;
    private List<Role> roles;
    private List<Menu> menus;
    private List<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (permissions != null&&permissions.size()>0) {
//            ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            for (int i = 0; i < permissions.size(); i++) {
//                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissions.get(i));
//                authorities.add(authority);
//            }
//            return authorities;
//        }else {
//            return new ArrayList<>();
//        }
        return CollUtil.isNotEmpty(permissions) ? permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()) : new ArrayList<>();
    }

    @Override
    public String getPassword() {
//        if (user!=null){
//            return user.getPassword();
//        }else {
//            return null;
//        }

//        return ObjUtil.isNotNull(user) ? user.getPassword() : null;

        return Optional.of(user).map(User::getPassword).orElse(null);
    }

    @Override
    public String getUsername() {
        return ObjUtil.isNotNull(user) ? user.getUserName() : null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
