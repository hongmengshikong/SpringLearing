package cn.edu.xcu.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password(passwordEncoder().encode("123")).authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password(passwordEncoder().encode("456")).authorities("ROLE_p2").build());
        return manager;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable());
        http.formLogin(login->login.usernameParameter("username")
                .passwordParameter("passwd")
                .loginPage("/toLogin")
                .loginProcessingUrl("/user/login").permitAll())
                .logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/"))
                .authorizeHttpRequests(auth->auth.requestMatchers("/r/r1").hasAnyAuthority("p1")
                        .requestMatchers("/r/r2").hasRole("p2")
                        .requestMatchers("/css/*","/images/*","/js/*").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
