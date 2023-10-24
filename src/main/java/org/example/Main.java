package org.example;

import org.example.config.DefualtAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DefualtAppConfig.class);
        context.getBean(ProfileWorker.class).doWork();
    }

}