package com.alevel.springMVC.sweater.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;


// Класс WebSecurityConfig аннотирован @EnableWebSecurity,
// чтобы включить поддержку веб-безопасности Spring Security и обеспечить интеграцию Spring MVC.
// Он также расширяет WebSecurityConfigurerAdapter и переопределяет несколько его методов,
// чтобы установить некоторые особенности конфигурации веб-безопасности.

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/registration").permitAll()
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

/*    // метод userDetailsService() устанавливает хранилище пользователей в памяти с одним пользователем.
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
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.jdbcAuthentication()
                .dataSource(dataSource) // дает возможность ходить в базу данных пользователей и искать их роли
                .passwordEncoder(NoOpPasswordEncoder.getInstance())  // шифрование паролей, применяем только для тестирования
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                // найти пользователя в бд по имени (username)
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u " +
                        "inner join user_role ur on u.id = ur.user_id where u.username=?");
                // получаем список пользователей с их ролями
    }
}