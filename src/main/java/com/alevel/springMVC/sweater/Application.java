package com.alevel.springMVC.sweater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication - это удобная аннотация, которая добавляет все следующее:
//
//    @Configuration маркирует класс как источник определений бина для контекста приложения.
//
//    @EnableAutoConfiguration указывает Spring Boot начинать добавление bean-компонентов
//    на основе настроек пути к классам, других bean-компонентов и различных настроек свойств.
//
//    @ComponentScan говорит Spring искать другие компоненты, конфигурации и сервисы в пакете sweater,
//    что позволяет ему находить контроллеры.
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
