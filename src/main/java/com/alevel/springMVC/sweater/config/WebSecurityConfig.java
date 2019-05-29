package com.alevel.springMVC.sweater.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


// Класс WebSecurityConfig аннотирован @EnableWebSecurity,
// чтобы включить поддержку веб-безопасности Spring Security и обеспечить интеграцию Spring MVC.
// Он также расширяет WebSecurityConfigurerAdapter и переопределяет несколько его методов,
// чтобы установить некоторые особенности конфигурации веб-безопасности.

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    // не требует никакой аутентификации

                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    // разрешаем доступ всем

                .and()
                    .logout()
                    .permitAll();
    }

    // метод userDetailsService() устанавливает хранилище пользователей в памяти с одним пользователем.
    // Этот пользователь получает имя пользователя «user», пароль «password» и роль «USER».
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}