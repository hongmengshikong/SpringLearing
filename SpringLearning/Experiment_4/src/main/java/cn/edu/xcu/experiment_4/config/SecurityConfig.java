package cn.edu.xcu.experiment_4.config;


import cn.edu.xcu.experiment_4.filter.JwtAuthenticationTokenFilter;
import cn.edu.xcu.experiment_4.filter.TokenLoginFilter;
import cn.edu.xcu.experiment_4.handler.TokenLogoutHandler;
import cn.edu.xcu.experiment_4.utils.RedisCache;
import cn.edu.xcu.experiment_4.utils.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
    @Autowired
    DataSource dataSource;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private TokenManager tokenManager;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password(passwordEncoder().encode("123")).authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password(passwordEncoder().encode("456")).authorities("ROLE_p2").build());
//        return manager;
//    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,AuthenticationManager manager) throws Exception {
        http.csrf(csrf->csrf.disable());
        http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .logout(logout->logout.logoutUrl("/user/logout")
                        .logoutSuccessHandler(logoutSuccessHandler)
                        .addLogoutHandler(new TokenLogoutHandler(redisCache, tokenManager)))
                .authorizeRequests(auth->auth.requestMatchers("/user/register","/vcode").permitAll()
                        .anyRequest().authenticated());
        http.addFilterBefore(new JwtAuthenticationTokenFilter(redisCache,tokenManager), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(new TokenLoginFilter(redisCache, tokenManager, manager), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(ex->ex.accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint));
        return http.build();
    }
    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository(){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }
}
