package com.example.formlogin.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
       // http.formLogin(c -> c.defaultSuccessUrl("/hello",true));
        //http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(c-> c.anyRequest().hasAuthority("write"));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var user1= User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        var user2=User.withUsername("jane")
                .password("5678")
                .authorities("write")
                .build();
        var uds=new InMemoryUserDetailsManager();
        uds.createUser(user1);
        uds.createUser(user2);
        return uds;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
